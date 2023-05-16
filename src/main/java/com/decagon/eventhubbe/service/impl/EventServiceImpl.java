package com.decagon.eventhubbe.service.impl;

import com.decagon.eventhubbe.domain.entities.Event;
import com.decagon.eventhubbe.domain.repository.EventRepository;
import com.decagon.eventhubbe.dto.EventDTO;
import com.decagon.eventhubbe.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {


    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    // Implementing the deletion of Event ----->
    @Override
    public void deleteEvent(EventDTO eventDTO) {
        Event eventToDelete = eventRepository
                .findById(eventDTO.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Event not found"));

        eventToDelete.setDeleted(eventDTO.isDeleted());
        eventRepository.save(eventToDelete);
    }

}
