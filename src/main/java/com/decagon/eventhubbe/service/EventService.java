package com.decagon.eventhubbe.service;

import com.decagon.eventhubbe.domain.entities.EventTicket;
import com.decagon.eventhubbe.dto.request.EventTicketRequest;

public interface EventService {
    EventTicketRequest getAllTicketByEvent(String ticketNumber);
}
