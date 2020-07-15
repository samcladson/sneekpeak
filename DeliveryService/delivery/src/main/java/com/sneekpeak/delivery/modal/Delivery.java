package com.sneekpeak.delivery.modal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Delivery {
    @Id
    private String id;
    private String orderId;
    private String productName;
    private String price;
    private Address address;
    private String quantity;

    public Delivery() {

    }

    public Delivery(String id, String orderId, String productName, String price, Address address, String quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productName = productName;
        this.price = price;
        this.address = address;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}