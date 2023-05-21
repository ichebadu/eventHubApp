package com.decagon.eventhubbe.service;

import com.decagon.eventhubbe.domain.entities.EventTicket;
import com.decagon.eventhubbe.dto.request.EventTicketRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventTicketService {
    EventTicketRequest getAllTicketByEvent(String ticketNumber);
    List<EventTicket> getTrackListEvent(String eventId);
    Page<EventTicket> getAllTickets(Pageable pageable);
    List<EventTicket> getAll();
}
