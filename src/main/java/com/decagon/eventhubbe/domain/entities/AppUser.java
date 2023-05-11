package com.decagon.eventhubbe.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AppUser {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    private String userType;

    @DBRef
    private List<Event> events;

    @DBRef
    private List<EventTicket> eventTickets;

    @DBRef
    private List<Payment> payments;

}
