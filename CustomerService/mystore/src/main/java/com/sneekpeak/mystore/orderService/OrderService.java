package com.sneekpeak.mystore.orderService;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderService extends MongoRepository<Orders, String> {

}