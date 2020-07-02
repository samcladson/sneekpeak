package com.sneekpeak.mystore.Orchestrator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.sneekpeak.mystore.model.Products;
import com.sneekpeak.mystore.orderService.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    final String POST_ORDER = "http://localhost:8080/orders/postOrder";
    final String UPDATE_ORDER = "http://localhost:8080/orders/updateOrder";
    final String DELETE_ORDER = "http://localhost:8080/orders/deleteOrder";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getAllProducts")
    public List<Object> getAllProducts() {
        Object[] data = restTemplate.getForObject(GET_ALL_PRODUCTS, Object[].class);
        return Arrays.asList(data);
    }

    @PostMapping("/placeOrder")
    public Object placeOrder(@RequestBody Orders order) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("ID", order.getProductId());
        Products data = restTemplate.getForObject(GET_SINGLE_PRODUCT, Products.class, param);
        if (Integer.parseInt(order.getOrderQuantity()) <= Integer.parseInt(data.getQuantity())) {
            restTemplate.postForObject(POST_ORDER, order, Orders.class);
            return "Success";
        } else {
            return "error";
        }

    }
}