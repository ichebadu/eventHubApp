package com.decagon.eventhubbe.controller;

import com.decagon.eventhubbe.domain.entities.Event;
import com.decagon.eventhubbe.dto.request.EventRequest;
import com.decagon.eventhubbe.dto.response.APIResponse;
import com.decagon.eventhubbe.dto.response.EventResponse;
import com.decagon.eventhubbe.service.EventService;
import com.decagon.eventhubbe.utils.PageConstant;
import com.decagon.eventhubbe.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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

    @GetMapping("/view-event")
    public ResponseEntity<APIResponse<PageUtils>> findAllEvents(
            @RequestParam(value = "pageNo", defaultValue = PageConstant.pageNo) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageConstant.pageSize) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageConstant.sortBy) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PageConstant.sortDir) String sortDir) {

        APIResponse<PageUtils> apiResponse = new APIResponse<>(eventService.publishEvent(pageNo, pageSize, sortBy, sortDir));
        return ResponseEntity.ok().body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<String>> updateEvent(
            @PathVariable String id,
            @RequestBody Event updateEvent
    ){
        APIResponse<String> apiResponse = new APIResponse<>(eventService.updateEvent(id, updateEvent));
        return new ResponseEntity<>(
                apiResponse, HttpStatus.OK
        );
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
