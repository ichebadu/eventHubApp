package com.decagon.eventhubbe.domain.repository;

import com.eventhub.bookingapp.domain.entities.EventTicket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventTicketRepository extends MongoRepository<EventTicket, String> {
}
