package com.decagon.eventhubbe.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    private String title;
    private String caption;
    private String description;
    private String organizer;
    private String category;
    private List<EventTicketRequest> tickets = new ArrayList<>();
    private String location;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private MultipartFile image;
}