package com.sneekpeak.delivery.Repository;

import com.sneekpeak.delivery.modal.Delivery;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface Repo extends MongoRepository<Delivery, String> {

}