package com.sneekpeak.payment.controller;

import java.util.List;
import java.util.Optional;

import com.sneekpeak.payment.modal.Payment;
import com.sneekpeak.payment.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping("/allAccount")
    public List<Payment> getAllAccount() {
        return service.getAllAccount();
    }

    @GetMapping("/getAccount/{id}")
    public Optional<Payment> getSingleAccount(@PathVariable String id) {
        return service.getSingleAccount(id);
    }

    @PostMapping("createAccount")
    public void createAccount(@RequestBody Payment payment) {
        service.createAccount(payment);
    }

    @PutMapping("/updateAccount/{id}")
    public void updateAccount(@PathVariable String id, @RequestBody Payment payment) {
        service.updateAccount(id, payment);
    }

    @DeleteMapping("/deleteAccount/{id}")
    public void deleteAccount(@PathVariable String id) {
        service.deleteAccount(id);
    }

    @PostMapping("/checkBalance/{id}")
    public Object checkBalance(@PathVariable String id, @RequestBody Payment pmt) {
        return service.checkBalance(id, pmt);
    }

    @PostMapping("/checkAccountDetails")
    public Object checkAccountDetails(@RequestBody Payment pmt) {
        return service.checkAccountDetails(pmt);

    }
}