package com.decagon.eventhubbe.service.impl;

import com.decagon.eventhubbe.ENUM.EventCategory;
import com.decagon.eventhubbe.config.CloudinaryConfig;
import com.decagon.eventhubbe.domain.entities.AppUser;
import com.decagon.eventhubbe.domain.entities.Event;
import com.decagon.eventhubbe.domain.entities.EventTicket;
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
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {


    private final EventRepository eventRepository;
    private final EventTicketRepository eventTicketRepository;
    private final AppUserServiceImpl appUserService;
    private final ModelMapper modelMapper;

    @Override
    public EventResponse create(EventRequest request) {
        AppUser user = appUserService.getUserByEmail(UserUtils.getUserEmailFromContext());
        Event event = Event.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .caption(request.getCaption())
                .appUser(user)
                .category(EventCategory.fromDisplayName(request.getCategory()))
                .location(request.getLocation())
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


}


