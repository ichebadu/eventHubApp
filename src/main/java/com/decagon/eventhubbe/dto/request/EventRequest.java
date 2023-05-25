package com.decagon.eventhubbe.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventRequest {
    private String title;
    private String caption;
    private String description;
    private String organizer;
    private String category;
    private List<EventTicketRequest> tickets;
    private String location;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
}