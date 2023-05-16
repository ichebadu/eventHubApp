package com.decagon.eventhubbe.domain.repository;


import com.decagon.eventhubbe.domain.entities.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {


    // Delete event by title
    void deleteByTitle(String title);

}
