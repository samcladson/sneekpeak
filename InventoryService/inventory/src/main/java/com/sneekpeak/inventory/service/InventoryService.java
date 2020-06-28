package com.sneekpeak.inventory.service;

import com.sneekpeak.inventory.modal.Products;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryService extends MongoRepository<Products, String> {

}