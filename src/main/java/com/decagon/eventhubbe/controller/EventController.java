package com.decagon.eventhubbe.controller;

import com.decagon.eventhubbe.dto.request.EventTicketRequest;
import com.decagon.eventhubbe.dto.response.APIResponse;
import com.decagon.eventhubbe.service.impl.EventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/ticket")
public class EventController {
    private final EventServiceImpl eventService;
    @GetMapping("/getTicket/{ticketNumber}")
    public ResponseEntity<APIResponse<EventTicketRequest>> getTicket(@PathVariable String ticketNumber){
        APIResponse<EventTicketRequest> apiResponse = new APIResponse<>(eventService.getAllTicketByEvent(ticketNumber));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
