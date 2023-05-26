package com.decagon.eventhubbe.controller;

import com.decagon.eventhubbe.domain.entities.Event;
import com.decagon.eventhubbe.dto.request.EventRequest;
import com.decagon.eventhubbe.dto.response.APIResponse;
import com.decagon.eventhubbe.dto.response.EventResponse;
import com.decagon.eventhubbe.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse<EventResponse>> createEvent(@RequestBody EventRequest eventRequest) {
        APIResponse<EventResponse> response = new APIResponse<>(eventService.create(eventRequest));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping(value = "/create/{eventId}/event-banner", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse<EventResponse>> addEventBanner(@PathVariable String eventId,
            @RequestParam("file") MultipartFile file){
        APIResponse<EventResponse> response = new APIResponse<>(eventService.addEventBanner(eventId,file));
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }


    // Implementing the deletion of Event ----->
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteEvent(
            @PathVariable String id) {
        APIResponse<String> apiResponse = new APIResponse<>(eventService.deleteEvent(id));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<APIResponse<List<Event>>> allevent() {
        APIResponse<List<Event>> apiResponse =
                new APIResponse<>(eventService.getAllEvent());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK
        );
    }

}
