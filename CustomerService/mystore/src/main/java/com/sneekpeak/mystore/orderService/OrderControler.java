package com.sneekpeak.mystore.orderService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class OrderControler {

    @Autowired
    private OrderService service;

    @GetMapping("/orders/allOrders")
    public List<Orders> getAllOrders() {
        return service.findAll();
    }

    @PostMapping("/orders/myOrders")
    public List<Orders> getMyOrders(@RequestBody Orders order) {
        Example<Orders> data = Example.of(order);
        return service.findAll(data);
    }

    @GetMapping("/orders/{id}")
    public Optional<Orders> getOrders(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping("/orders/postOrder")
    public Object addOrder(@RequestBody Orders order) {
        order.setStatus("placed");
        Orders o = service.save(order);
        return o;

    }

    @PutMapping("/orders/updateOrder")
    public void updateOrder(@PathVariable String id, @RequestBody Orders order) {
        service.save(order);
    }

    @DeleteMapping("orders/deleteOrder")
    public void deleteOrder(@PathVariable String id) {
        service.deleteById(id);
    }

}