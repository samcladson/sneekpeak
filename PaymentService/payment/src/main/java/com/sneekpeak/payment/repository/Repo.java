package com.sneekpeak.payment.repository;

import com.sneekpeak.payment.modal.Payment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends MongoRepository<Payment, String> {

}