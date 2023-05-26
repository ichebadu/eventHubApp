package com.decagon.eventhubbe.service.impl;

import com.decagon.eventhubbe.ENUM.EventCategory;
import com.decagon.eventhubbe.config.CloudinaryConfig;
import com.decagon.eventhubbe.domain.entities.AppUser;
import com.decagon.eventhubbe.domain.entities.Event;
import com.decagon.eventhubbe.domain.entities.EventTicket;
import com.decagon.eventhubbe.domain.entities.geoLocation.GeoResponse;
import com.decagon.eventhubbe.domain.entities.geoLocation.Result;
import com.decagon.eventhubbe.domain.repository.EventRepository;
import com.decagon.eventhubbe.domain.repository.EventTicketRepository;
import com.decagon.eventhubbe.dto.request.EventRequest;
import com.decagon.eventhubbe.dto.request.EventTicketRequest;
import com.decagon.eventhubbe.dto.response.EventResponse;
import com.decagon.eventhubbe.exception.EventNotFoundException;
import com.decagon.eventhubbe.exception.UnauthorizedException;
import com.decagon.eventhubbe.service.EventService;
import com.decagon.eventhubbe.utils.DateUtils;
import com.decagon.eventhubbe.utils.EventUtils;
import com.decagon.eventhubbe.utils.PageUtils;
import com.decagon.eventhubbe.utils.UserUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private static final Object API_KEY = "AIzaSyA22GBhIK3LwSHcYDlB8UYJ4x1IoGeuqvM";

    private final EventRepository eventRepository;
    private final EventTicketRepository eventTicketRepository;
    private final AppUserServiceImpl appUserService;
    private final ModelMapper modelMapper;

    @Override
    public EventResponse create(EventRequest request) {
        AppUser user = appUserService.getUserByEmail(UserUtils.getUserEmailFromContext());
        GeoResponse geoDetails = getGeoDetails(request);
        String actualLocation = extractActualLocation(geoDetails);
        Event event = Event.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .caption(request.getCaption())
                .appUser(user)
                .category(EventCategory.fromDisplayName(request.getCategory()))
                .location(actualLocation)
                .organizer(request.getOrganizer())
                .isDeleted(false)
                .startDate(request.getStartDate())
                .startTime(request.getStartTime())
                .endDate(request.getEndDate())
                .endTime(request.getEndTime())
                .createdAt(DateUtils.saveDate(LocalDateTime.now()))
                .build();
        Event savedEvent = eventRepository.save(event);
        List<EventTicketRequest> ticketRequest = request.getTickets();
        ticketRequest.forEach(ticket -> eventTicketRepository.save(
                EventTicket.builder()
                        .event(savedEvent)
                        .description(ticket.getDescription())
                        .quantity(ticket.getQuantity())
                        .ticketClass(ticket.getTicketClass())
                        .ticketPrice(ticket.getTicketPrice())
                        .build()
        ));
        savedEvent.setEventTickets(eventTicketRepository.findAllByEvent(savedEvent));
        return modelMapper.map(eventRepository.save(savedEvent), EventResponse.class);
    }

    @Override
    public EventResponse addEventBanner(String eventId, MultipartFile file){
        Event event = eventRepository
                .findById(eventId)
                .orElseThrow(() ->
                        new EventNotFoundException(eventId));
        CloudinaryConfig cloudinaryConfig = new CloudinaryConfig();
        event.setBannerUrl(cloudinaryConfig.imageLink(file,event.getId()));
        Event savedEvent = eventRepository.save(event);
        return modelMapper.map(eventRepository.save(savedEvent), EventResponse.class);
    }

    // Implementing the deletion of Event ----->
    @Transactional
    @Override
    public String deleteEvent(String id) {

        Event eventToDelete = eventRepository
                .findById(id)
                .orElseThrow(() ->
                        new EventNotFoundException(id));
        AppUser appUser = appUserService.getUserByEmail(UserUtils.getUserEmailFromContext());
        if(!eventToDelete.getAppUser().equals(appUser)){
            throw new UnauthorizedException("Unauthorized! Event created by another user");
        }
        eventToDelete.setDeleted(true);
        return "Event with title : "+eventToDelete.getTitle()+" deleted successfully";
    }

    @Override
    public Event findEventById(Integer eventId) {
       return eventRepository.findEventById(eventId);
    }

    @Override
    public PageUtils publishEvent(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Page <Event> eventPage = eventRepository.findAll(PageRequest.of(pageNo, pageSize, sort));
        List<Event> events = new ArrayList<>();

        eventPage.getContent().forEach(event -> {
            if (EventUtils.eventValidation(event)) {
                events.add(event);
            }
        });
        System.out.println(events.get(0).getCaption());
        List<EventResponse> eventResponses = events.stream().map(event -> modelMapper.map(event, EventResponse.class))
                .collect(Collectors.toList());
        return PageUtils.builder()
                        .content(eventResponses)
                        .pageNo(eventPage.getNumber())
                        .pageSize(eventPage.getSize())
                .totalElements(eventPage.getTotalElements())
                .totalPage(eventPage.getTotalPages())
                .isLast(eventPage.isLast())
                .build();

    }

    @Transactional
    @Override
    public String updateEvent(String id, Event updateEvent) {
        Event eventToUpdate = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

        eventToUpdate.setTitle(updateEvent.getTitle());
        eventToUpdate.setCaption(updateEvent.getCaption());
        eventToUpdate.setDescription(updateEvent.getDescription());
        eventToUpdate.setOrganizer(updateEvent.getOrganizer());
        eventToUpdate.setCategory(updateEvent.getCategory());
        eventToUpdate.setLocation(updateEvent.getLocation());
        eventToUpdate.setStartDate(updateEvent.getStartDate());
        eventToUpdate.setEndDate(updateEvent.getEndDate());
        eventToUpdate.setStartTime(updateEvent.getStartTime());
        eventToUpdate.setEndTime(updateEvent.getEndTime());
        eventToUpdate.setBannerUrl(updateEvent.getBannerUrl());
        eventToUpdate.setAppUser(updateEvent.getAppUser());
        eventToUpdate.setEventTickets(updateEvent.getEventTickets());

        eventRepository.save(eventToUpdate);

        return "Event with ID: " + id + " updated successfully";
    }


    public GeoResponse getGeoDetails(@RequestParam EventRequest location) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("maps.googleapis.com")
                .path("/maps/api/geocode/json")
                .queryParam("key", API_KEY)
                .queryParam("address", location.getLocation())
                .build();
        ResponseEntity<GeoResponse> response = new RestTemplate().getForEntity(uri.toUriString(), GeoResponse.class);

}


