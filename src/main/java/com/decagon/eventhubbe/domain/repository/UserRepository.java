package com.decagon.eventhubbe.domain.repository;


import com.decagon.eventhubbe.domain.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
