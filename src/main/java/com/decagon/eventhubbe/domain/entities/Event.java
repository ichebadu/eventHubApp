package com.decagon.eventhubbe.domain.entities;

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

    @Indexed(unique = true)
    private String title;

    private String description;
    private String organizer;
    private String category;
    private String ticketClass;
    private LocalDateTime dateTime;
    private String location;
    private BigDecimal ticketPrice;
    private String bannerUrl;

    @DBRef
    private AppUser appUser;

    @DBRef
    private List<EventTicket> eventTickets;

}
