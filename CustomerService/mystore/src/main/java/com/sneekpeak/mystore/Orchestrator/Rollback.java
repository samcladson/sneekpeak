package com.sneekpeak.mystore.Orchestrator;

import java.util.HashMap;

import com.sneekpeak.mystore.model.Payment;
import com.sneekpeak.mystore.model.Products;
import com.sneekpeak.mystore.orderService.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Rollback {

    @Autowired
    private HashMap<String, Object> memory;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    public Object rollbackFunction() {
        HashMap<String, String> res = new HashMap<>();
        if (memory.get("account") != null) {
            restTemplate.put(env.getProperty("UPDATE_ACCOUNT"), memory.get("account"), Payment.class,
                    memory.get("accountNo"));
            res.put("message_1", "Payment rollbacked");
        }
        if (memory.get("product") != null) {
            restTemplate.put(env.getProperty("UPDATE_PRODUCT"), memory.get("product"), Products.class,
                    memory.get("productId"));
            res.put("message_2", "Product rollbacked");
        }
        // if (memory.get("orderId") != null) {
        // Orders order = restTemplate.getForObject(env.getProperty("GET_SINGLE_ORDER"),
        // Orders.class,
        // memory.get("orderId"));
        // order.setStatus("canceled");
        // restTemplate.put(env.getProperty("UPDATE_ORDER"), Orders.class,
        // memory.get("orderId"));
        // res.put("message_3", "Order rollbacked");
        // }
        return res;
    };
}