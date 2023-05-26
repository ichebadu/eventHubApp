package com.decagon.eventhubbe.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventTicketRequest {
    private String ticketClass;
    private String description;
    private Double ticketPrice;
    private Integer quantity;
}
