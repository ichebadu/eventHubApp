package com.decagon.eventhubbe.controller;

import com.decagon.eventhubbe.dto.request.SaveTicketRequest;
import com.decagon.eventhubbe.dto.response.APIResponse;
import com.decagon.eventhubbe.dto.response.SaveTicketResponse;
import com.decagon.eventhubbe.dto.response.TicketsSalesResponse;
import com.decagon.eventhubbe.service.EventTicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/ticket")
@SecurityRequirement(
        name = "Bear Authentication"
)
@Tag(
        name = "CRUD REST APIs for Event Resource"
)
public class EventTicketController {
    private final EventTicketService eventTicketService;

    @Operation(
            summary = "Delete Event",
            description = "Delete an existing event."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @GetMapping("/view-event-sales/{eventId}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<APIResponse<List<TicketsSalesResponse>>> trackTicket
            (@PathVariable String eventId){
        APIResponse<List<TicketsSalesResponse>> apiResponse = new APIResponse<>
                (eventTicketService.trackTicketSales(eventId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/save-tickets")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<APIResponse<String>> saveTickets(@RequestBody SaveTicketRequest request){
        APIResponse<String> apiResponse = new APIResponse<>(eventTicketService.saveTicket(request));
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/get-saved-tickets")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<APIResponse<List<SaveTicketResponse>>> getBookedTicket(){
        APIResponse<List<SaveTicketResponse>> apiResponse = new APIResponse<>(eventTicketService.getSavedTickets());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
