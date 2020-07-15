package com.sneekpeak.mystore.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.sneekpeak.mystore.model.User;

@Service
public interface UserService extends MongoRepository<User, String> {

}
