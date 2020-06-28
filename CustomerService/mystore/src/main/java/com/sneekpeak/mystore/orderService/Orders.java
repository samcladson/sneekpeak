package com.sneekpeak.mystore.orderService;

import java.util.Date;
import java.util.Random;

public class Orders {
    private String orderId = "O" + new Random().nextInt(9999);
    private String customerId;
    private String productId;
    private String productName;
    private String productPrice;
    private String orderQuantity;
    private Date orederDate;

    public Orders() {

    }

    public Orders(String orderId, String customerId, String productId, String productName, String productPrice,
            String orderQuantity, Date orederDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.orderQuantity = orderQuantity;
        this.orederDate = orederDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Date getOrederDate() {
        return orederDate;
    }

    public void setOrederDate(Date orederDate) {
        this.orederDate = orederDate;
    }
}