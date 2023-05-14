package com.decagon.eventhubbe.domain.repository;


import com.decagon.eventhubbe.domain.entities.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AppUserRepository extends MongoRepository<AppUser, String> {
    Optional<AppUser> findByEmail(String email);
}
