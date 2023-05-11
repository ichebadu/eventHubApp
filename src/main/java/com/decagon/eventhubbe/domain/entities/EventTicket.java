package com.decagon.eventhubbe.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "tickets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EventTicket {

    @Id
    private String id;

    @Indexed(unique = true)
    private String ticketNumber;

    private String ticketType;
    private BigDecimal price;
    private Date purchasedDate;

    @DBRef
    private Event event;

    @DBRef
    private AppUser appUser;
}
