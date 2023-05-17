package com.decagon.eventhubbe.service;

import com.decagon.eventhubbe.dto.EventDTO;
import org.springframework.stereotype.Service;

@Service
public interface EventService {

    //deletion of event ------>
    String deleteEvent(String id);
}
