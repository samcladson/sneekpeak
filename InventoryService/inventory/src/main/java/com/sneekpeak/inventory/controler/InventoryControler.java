package com.sneekpeak.inventory.controler;

import java.util.List;
import java.util.Optional;

import com.sneekpeak.inventory.modal.Products;
import com.sneekpeak.inventory.service.InventoryService;

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
public class InventoryControler {

    @Autowired
    private InventoryService service;

    @GetMapping("/products/allProducts")
    public List<Products> getAllProducts() {
        return service.findAll();
    }

    @GetMapping("/products/{id}")
    public Optional<Products> getAllProducts(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping("/products/addProduct")
    public void addProduct(@RequestBody Products product) {
        service.save(product);
    }

    @PutMapping("/products/updateProduct/{id}")
    public String updateProduct(@PathVariable String id, @RequestBody Products product) {
        service.save(product);
        return "updated";
    }

    @DeleteMapping("/products/deleteProduct/{id}")
    public void deleteProducts(@PathVariable String id) {
        service.deleteById(id);
    }
}