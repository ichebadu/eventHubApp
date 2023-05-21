package com.decagon.eventhubbe.controller;

import com.decagon.eventhubbe.domain.entities.EventTicket;
import com.decagon.eventhubbe.dto.request.EventTicketRequest;
import com.decagon.eventhubbe.dto.response.APIResponse;
import com.decagon.eventhubbe.service.impl.EventServiceImpl;
import com.decagon.eventhubbe.service.impl.EventTicketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/ticket")
public class EventTicketController {
    private final EventTicketServiceImpl eventService;
    @GetMapping("/getTicket/{ticketNumber}")
    public ResponseEntity<APIResponse<EventTicketRequest>> getTicket(@PathVariable String ticketNumber){
        APIResponse<EventTicketRequest> apiResponse = new APIResponse<>(eventService.getAllTicketByEvent(ticketNumber));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/list_of_Ticket/{event_Id}")
    public ResponseEntity<APIResponse<List<EventTicket>>>  list(@PathVariable String event_Id){
        APIResponse<List<EventTicket>> apiResponse = new APIResponse<>(eventService.getTrackListEvent(event_Id));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("getTicket")
    public ResponseEntity<APIResponse<Page<EventTicket>>> getPageTicket(Pageable pageable){
        APIResponse<Page<EventTicket>> getTicket = new APIResponse<>(eventService.getAllTickets(pageable));
        return new ResponseEntity<>(getTicket,HttpStatus.OK);

    }

    @GetMapping("/getAllTicket")
    public ResponseEntity<APIResponse<List<EventTicket>>> all(){
        APIResponse<List<EventTicket>> getAll = new APIResponse<>(eventService.getAll());
        return new ResponseEntity<>(getAll,HttpStatus.OK);
    }
}
