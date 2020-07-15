package com.sneekpeak.delivery.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.sneekpeak.delivery.Repository.Repo;
import com.sneekpeak.delivery.modal.Delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class DeliveryController {

    @Autowired
    private Repo service;

    @GetMapping("/allDelivery")
    public List<Delivery> getAll() {
        return service.findAll();
    }

    @GetMapping("/singleDelivery/{id}")
    public Optional<Delivery> getOne(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping("/createDelivery")
    public Object create(@RequestBody Delivery data) {
        service.save(data);
        HashMap<String, Object> res = new HashMap<>();
        res.put("status", HttpStatus.OK);
        res.put("message", "successfully created");
        return res;
    }

    @PutMapping("/updateDelivery/{id}")
    public Object update(@PathVariable String id, @RequestBody Delivery data) {
        service.save(data);
        HashMap<String, Object> res = new HashMap<>();
        res.put("status", HttpStatus.OK);
        res.put("message", "successfully updated");
        return res;
    }

    @DeleteMapping("deleteDelivery/{id}")
    public Object delete(@PathVariable String id) {
        service.deleteById(id);
        HashMap<String, Object> res = new HashMap<>();
        res.put("status", HttpStatus.OK);
        res.put("message", "Successfully deleted");
        return res;
    }
}