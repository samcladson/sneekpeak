package com.sneekpeak.mystore.Orchestrator;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class Orchestrator {
    // product apis
    final String GET_ALL_PRODUCTS = "http://localhost:8081/products/allProducts";
    final String GET_SINGLE_PRODUCT = "http://localhost:8081/products/{ID}";
    final String POST_PRODUCT = "http://localhost:8081/products/addProduct";
    final String UPDATE_PRODUCT = "http://localhost:8081/products/updateProduct";
    final String DELETE_PRODUCT = "http://localhost:8081/products/deleteProduct";

    // order apis
    final String GET_ALL_ORDERS = "http://localhost:8080/orders/allOrders";
    final String GET_SINGLE_ORDER = "http://localhost:8080/orders/{ID}";
    final String POST_ORDER = "http://localhost:8080/orders/addOrder";
    final String UPDATE_ORDER = "http://localhost:8080/orders/updateOrder";
    final String DELETE_ORDER = "http://localhost:8080/orders/deleteOrder";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public List<Object> test() {
        Object[] data = restTemplate.getForObject(GET_ALL_PRODUCTS, Object[].class);
        return Arrays.asList(data);
    }
}