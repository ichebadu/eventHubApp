package com.decagon.eventhubbe.service;

import com.decagon.eventhubbe.dto.request.EventTicketRequest;

public interface EventTicketService {
    EventTicketRequest getAllTicketByEvent(String ticketNumber);
}
