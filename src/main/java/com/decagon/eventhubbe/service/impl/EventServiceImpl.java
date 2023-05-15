package com.decagon.eventhubbe.service.impl;

import com.decagon.eventhubbe.domain.entities.EventTicket;
import com.decagon.eventhubbe.domain.repository.EventTicketRepository;
import com.decagon.eventhubbe.dto.request.EventTicketRequest;
import com.decagon.eventhubbe.service.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventTicketRepository eventTicketRepository;
    private final ModelMapper modelMapper;
    @Override
    public EventTicketRequest getAllTicketByEvent(String ticketNumber){
        EventTicket eventTicket = eventTicketRepository.findEventTicketByTicketNumber(ticketNumber);
        return modelMapper.map(eventTicket,EventTicketRequest.class);
    }

}
