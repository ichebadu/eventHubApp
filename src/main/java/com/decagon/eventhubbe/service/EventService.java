package com.decagon.eventhubbe.service;

import com.decagon.eventhubbe.domain.entities.Event;
import com.decagon.eventhubbe.dto.request.EventRequest;
import com.decagon.eventhubbe.dto.response.EventResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface EventService {

    EventResponse create(EventRequest request);

    EventResponse addEventBanner(String eventId, MultipartFile file);
    //deletion of event ------>
    String deleteEvent(String id);

}
