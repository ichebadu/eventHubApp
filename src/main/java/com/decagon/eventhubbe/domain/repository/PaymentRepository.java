package com.decagon.eventhubbe.domain.repository;


import com.decagon.eventhubbe.domain.entities.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
}
