package com.decagon.eventhubbe.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventTicketRequest {
    private String ticketClass;
    private String description;
    private Double ticketPrice;
    private Integer quantity;
}
