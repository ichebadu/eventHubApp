package com.decagon.eventhubbe.controller;

import com.decagon.eventhubbe.dto.EventDTO;
import com.decagon.eventhubbe.dto.response.APIResponse;
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
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteEvent(
            @PathVariable String id) {
        APIResponse<String> apiResponse =
                new APIResponse<>(eventService.deleteEvent(id));
        return new ResponseEntity<>(
                apiResponse,
                HttpStatus.OK
        );
    }

}
