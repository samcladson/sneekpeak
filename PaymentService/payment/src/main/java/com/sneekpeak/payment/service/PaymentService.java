package com.sneekpeak.payment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.sneekpeak.payment.modal.Payment;
import com.sneekpeak.payment.repository.Repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private Repo repository;

    public List<Payment> getAllAccount() {
        return repository.findAll();
    }

    public Optional<Payment> getSingleAccount(String id) {
        return repository.findById(id);

    }

    public void createAccount(Payment payment) {
        repository.save(payment);
    }

    public void updateAccount(String id, Payment payment) {
        repository.save(payment);
    }

    public void deleteAccount(String id) {
        repository.deleteById(id);
    }

    public Object checkBalance(String id, Payment pmt) {
        Optional<Payment> accountData = repository.findById(id);
        if (pmt.getBalance() <= accountData.get().getBalance()) {
            // Float b = balance.get().getBalance() - pmt.getBalance();
            // balance.get().setBalance(b);
            HashMap<String, Object> data = new HashMap<>();
            data.put("STATUS", HttpStatus.OK);
            data.put("message", "Amount Available");
            data.put("accountNo", accountData.get().getAccountNumber());
            return data;
        } else {
            HashMap<String, Object> data = new HashMap<>();
            data.put("STATUS", HttpStatus.NOT_FOUND);
            data.put("message", "Insuffecient Balance");
            data.put("accountNo", accountData.get().getAccountNumber());
            return data;
        }

    }

    public Object checkAccountDetails(Payment pmt) {
        Example<Payment> dataObject = Example.of(pmt);
        Boolean data = repository.exists(dataObject);
        if (data) {
            Example<Payment> accData = Example.of(pmt);
            Optional<Payment> accno = repository.findOne(accData);
            HashMap<String, Object> res = new HashMap<>();
            res.put("STATUS", HttpStatus.OK);
            res.put("message", "Valid Account");
            res.put("accNo", accno.get().getAccountNumber());
            return res;
        } else {
            HashMap<String, Object> res = new HashMap<>();
            res.put("STATUS", HttpStatus.NOT_FOUND);
            res.put("message", "Could not find account, pleace check account details");
            return res;
        }
    }

}