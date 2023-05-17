package com.decagon.eventhubbe.service.impl;

import com.decagon.eventhubbe.domain.entities.AppUser;
import com.decagon.eventhubbe.domain.entities.Event;
import com.decagon.eventhubbe.domain.repository.AppUserRepository;
import com.decagon.eventhubbe.domain.repository.EventRepository;
import com.decagon.eventhubbe.dto.EventDTO;
import com.decagon.eventhubbe.exception.EventNotFoundException;
import com.decagon.eventhubbe.exception.UnauthorizedException;
import com.decagon.eventhubbe.service.EventService;
import com.decagon.eventhubbe.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {


    private final EventRepository eventRepository;
    private final AppUserServiceImpl appUserService;


    // Implementing the deletion of Event ----->
    @Transactional
    @Override
    public String deleteEvent(String id) {

        Event eventToDelete = eventRepository
                .findById(id)
                .orElseThrow(() ->
                        new EventNotFoundException(id));
        AppUser appUser = appUserService.findByEmail(UserUtils.getUserEmailFromContext());
        if(!eventToDelete.getAppUser().equals(appUser)){
            throw new UnauthorizedException("Unauthorized! Event created by another user");
        }
        eventToDelete.setDeleted(true);
        return "Event with title : "+eventToDelete.getTitle()+" deleted successfully";
    }

}
