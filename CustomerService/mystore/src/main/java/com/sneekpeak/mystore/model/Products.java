package com.sneekpeak.mystore.model;

public class Products {
    private String id;
    private String product_name;
    private String product_description;
    private String quantity;
    private String price;

    public Products() {

    }

    public Products(String id, String product_name, String product_description, String quantity, String price) {
        this.id = id;
        this.product_name = product_name;
        this.product_description = product_description;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}