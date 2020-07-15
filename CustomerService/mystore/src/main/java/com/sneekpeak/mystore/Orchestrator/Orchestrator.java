package com.sneekpeak.mystore.Orchestrator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.sneekpeak.mystore.model.Payment;
import com.sneekpeak.mystore.model.Products;
import com.sneekpeak.mystore.orderService.Orders;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class Orchestrator {

    @Autowired
    private HashMap<String, Object> memory;

    @Autowired
    private Environment env;

    @Autowired
    private Rollback rollback;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getAllProducts")
    public List<Object> getAllProducts() {
        Object[] data = restTemplate.getForObject(env.getProperty("GET_ALL_PRODUCTS"), Object[].class);
        return Arrays.asList(data);
    }

    @GetMapping("/myOrders/{userId}")
    public Object getMyOrders(@PathVariable String userId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("customerId", userId);
        Object data = restTemplate.postForObject(env.getProperty("GET_MY_ORDERS"), params, Object.class);
        return data;
    }

    @PostMapping("/checkQuantity")
    public Object placeOrder(@RequestBody Orders order) throws ResourceAccessException {
        try {
            HashMap<String, Object> param = new HashMap<>();
            param.put("ID", order.getProductId());
            Products data = restTemplate.getForObject(env.getProperty("GET_SINGLE_PRODUCT"), Products.class, param);
            int oldQty = Integer.parseInt(data.getQuantity());
            int newQty = Integer.parseInt(order.getOrderQuantity());
            if (newQty <= oldQty) {
                // deducting product quantity
                memory.put("customerId", order.getCustomerId());
                memory.put("product", data);
                memory.put("order", order);
                memory.put("orderPrice", Float.parseFloat(order.getProductPrice()));
                memory.put("newOrderQuantity", newQty);
                memory.put("productQuantity", oldQty);

                // if(result!=null){
                HashMap<String, Object> res = new HashMap<>();
                res.put("STATUS", new ResponseEntity<>(HttpStatus.OK));
                res.put("message", "Quantity Available");
                return res;
                // }else{
                // return null;
                // }
            } else {
                HashMap<String, Object> res = new HashMap<>();
                res.put("STATUS", new ResponseEntity<>(HttpStatus.NOT_FOUND));
                res.put("message", "Unsuffecient Quantity");
                return res;
            }
        } catch (ResourceAccessException e) {
            return "Server is not started";
        }

    }

    @PostMapping("/placeOrder")
    public Object placeOrder(@RequestBody Object obj) {
        Object data = restTemplate.postForObject(env.getProperty("CHECK_ACCOUNT"), obj, Object.class);
        return data;
    }

    @PostMapping("/checkBalance/{id}")
    public Object checkBalance(@PathVariable String id, @RequestBody Object obj) {
        String data = restTemplate.postForObject(env.getProperty("CHECK_BALANCE"), obj, String.class, id);
        JSONObject ob = new JSONObject(data);
        String accNo = ob.getString("accountNo");
        if (ob.getString("STATUS").equals("OK")) {
            Payment newObj = restTemplate.getForObject(env.getProperty("GET_ACCOUNT"), Payment.class, accNo);
            memory.put("account", newObj);
            memory.put("accountNo", newObj.getAccountNumber());
            memory.put("balance", newObj.getBalance());
            Gson g = new Gson();
            return g.fromJson(data, Object.class);
        } else {
            Gson g = new Gson();
            return g.fromJson(data, Object.class);
        }

    }

    @GetMapping("/completeOrder")
    public Object complete() throws NullPointerException {
        try {
            // balance operation
            Float orderPrice = Float.parseFloat(memory.get("orderPrice").toString());
            Float available = Float.parseFloat(memory.get("balance").toString());
            Float balance = available - orderPrice;

            // product update
            int productAvailable = Integer.parseInt(memory.get("productQuantity").toString());
            int orderQuantity = Integer.parseInt(memory.get("newOrderQuantity").toString());
            String newQuantity = Integer.toString(productAvailable - orderQuantity);

            Payment payment = restTemplate.getForObject(env.getProperty("GET_ACCOUNT"), Payment.class,
                    memory.get("accountNo").toString());
            payment.setBalance(balance);
            JSONObject o = new JSONObject(memory.get("product"));
            Products product = restTemplate.getForObject(env.getProperty("GET_SINGLE_PRODUCT"), Products.class,
                    o.getString("id"));
            product.setQuantity(newQuantity);

            restTemplate.put(env.getProperty("UPDATE_ACCOUNT"), payment, Payment.class, payment.getAccountNumber());
            restTemplate.put(env.getProperty("UPDATE_PRODUCT"), product, Products.class, product.getId());
            Orders data = restTemplate.postForObject(env.getProperty("CREATE_ORDER"), memory.get("order"),
                    Orders.class);
            HashMap<String, Object> res = new HashMap<>();
            if (data != null) {

                res.put("STATUS", HttpStatus.OK);
                res.put("message", "successfully placed");
                memory.put("order", data);
            } else {
                res.put("STATUS", HttpStatus.NOT_FOUND);
                res.put("message", "Error placing order");
                // rollback
                rollback.rollbackFunction();

            }
            return res;
        } catch (NullPointerException e) {

            return rollback.rollbackFunction();
        }
    }

    @GetMapping("/test")
    public Object test() {
        return memory;
    }

    @GetMapping("/rollback")
    public Object rollbacktest() {
        return rollback.rollbackFunction();
    }

}