package com.decagon.eventhubbe.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private String userType;

    @DBRef
    private List<Event> events;

    @DBRef
    private List<EventTicket> eventTickets;

    @DBRef
    private List<Payment> payments;

}
