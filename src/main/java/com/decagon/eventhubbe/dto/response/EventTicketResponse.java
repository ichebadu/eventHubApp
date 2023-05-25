package com.decagon.eventhubbe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventTicketResponse {
    private String id;
    private String ticketClass;
    private String description;
    private Double ticketPrice;
    private Integer quantity;
}
