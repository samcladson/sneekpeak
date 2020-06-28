package com.sneekpeak.mystore.orderService;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderService extends MongoRepository<Orders, String> {

}