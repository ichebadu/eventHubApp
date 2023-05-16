package com.decagon.eventhubbe.controller;

import com.decagon.eventhubbe.dto.EventDTO;
import com.decagon.eventhubbe.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    // Implementing the deletion of Event ----->
    @DeleteMapping("/id")
    public ResponseEntity<String> deleteEvent(
            @PathVariable String id,
            @RequestBody EventDTO eventDTO) {

        eventDTO.setId(id);
        eventDTO.setDeleted(true);

        eventService.deleteEvent(eventDTO);

        return new ResponseEntity<>(
                "Event deleted successfully",
                HttpStatus.OK
        );
    }

}
