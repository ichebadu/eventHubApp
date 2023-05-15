package com.decagon.eventhubbe.dto.request;

import com.decagon.eventhubbe.domain.entities.AppUser;
import com.decagon.eventhubbe.domain.entities.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
public class EventTicketRequest {
    private String ticketNumber;
    private String ticketType;
    private BigDecimal price;
    private Date purchasedDate;
    private Event event;

    private AppUser appUser;
}
