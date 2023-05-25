package com.decagon.eventhubbe.domain.entities;

import com.decagon.eventhubbe.ENUM.EventCategory;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "events")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Event {

    @Id
    private String id;
    private String title;
    private String caption;
    private String description;
    private String organizer;
    private EventCategory category;
    private String location;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String bannerUrl;
    private String createdAt;
    private boolean isDeleted;

    @DBRef
    private AppUser appUser;

    @DBRef
    private List<EventTicket> eventTickets;

}
