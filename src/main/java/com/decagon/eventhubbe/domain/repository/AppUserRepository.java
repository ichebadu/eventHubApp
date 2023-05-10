package com.decagon.eventhubbe.domain.repository;


import com.decagon.eventhubbe.domain.entities.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppUserRepository extends MongoRepository<AppUser, String> {
}
