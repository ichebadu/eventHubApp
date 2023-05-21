package com.decagon.eventhubbe.domain.repository;

import com.decagon.eventhubbe.domain.entities.EventTicket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface EventTicketRepository extends MongoRepository<EventTicket, String> {


}
