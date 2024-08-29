package com.skillstorm.warehouse_management.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.warehouse_management.models.Product;
import com.skillstorm.warehouse_management.services.ProductService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/product")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://mystery-box-warehouses-frontend.s3-website-us-east-1.amazonaws.com"})
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = service.findAll();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable int id) {
        Optional<Product> product = service.findById(id);
        if (product.isPresent())
            return ResponseEntity.ok(product.get());
        else 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        logger.debug("=== POST request to /products with Product of " + product + " ===");
        Product newProduct = service.save(product);
        return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> update(@Valid @PathVariable int id, @RequestBody Product product) {
        service.update(id, product);
        return new ResponseEntity<Integer>(id, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        service.deleteById(id);
    }

}
