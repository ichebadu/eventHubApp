package com.decagon.eventhubbe.domain.repository;


import com.decagon.eventhubbe.domain.entities.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
}
