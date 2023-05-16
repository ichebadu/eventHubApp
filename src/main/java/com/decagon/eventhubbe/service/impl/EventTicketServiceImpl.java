package com.decagon.eventhubbe.service.impl;

import com.decagon.eventhubbe.domain.entities.Event;
import com.decagon.eventhubbe.domain.entities.EventTicket;
import com.decagon.eventhubbe.domain.repository.EventRepository;
import com.decagon.eventhubbe.domain.repository.EventTicketRepository;
import com.decagon.eventhubbe.dto.request.EventTicketRequest;
import com.decagon.eventhubbe.service.EventTicketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EventTicketServiceImpl implements EventTicketService {
    private final EventTicketRepository eventTicketRepository;
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    @Override
    public EventTicketRequest getAllTicketByEvent(String ticketNumber){
        EventTicket eventTicket = eventTicketRepository.findEventTicketByTicketNumber(ticketNumber);
        return modelMapper.map(eventTicket,EventTicketRequest.class);
    }

    public List<EventTicket> getTrackListEvent(String eventId){
        Event event = eventRepository.findById(eventId).orElseThrow(()-> new RuntimeException("EVENT NOT FOUND"));
        return event.getEventTickets();

    }
}
