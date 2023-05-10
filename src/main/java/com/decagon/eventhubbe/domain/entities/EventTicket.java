package com.decagon.eventhubbe.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
