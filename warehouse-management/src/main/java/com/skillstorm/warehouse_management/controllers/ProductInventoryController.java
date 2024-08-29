package com.skillstorm.warehouse_management.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

import com.skillstorm.warehouse_management.models.ProductInventory;
import com.skillstorm.warehouse_management.services.ProductInventoryService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/product_inventory")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://mystery-box-warehouses-frontend.s3-website-us-east-1.amazonaws.com"})
public class ProductInventoryController {

        
    private final Logger logger = LoggerFactory.getLogger(ProductInventoryController.class);
    private ProductInventoryService service;

    public ProductInventoryController(ProductInventoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductInventory>> findAll() {
        List<ProductInventory> productInventories = service.findAll();
        return new ResponseEntity<List<ProductInventory>>(productInventories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductInventory> findById(@PathVariable int id) {
        Optional<ProductInventory> warehouse = service.findById(id);
        if (warehouse.isPresent())
            return ResponseEntity.ok(warehouse.get());
        else 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<ProductInventory> create(@Valid @RequestBody ProductInventory productInventory) {
        logger.debug("=== POST request to /warehouses with ProductInventory of " + productInventory + " ===");
        ProductInventory newProductInventory = service.save(productInventory);
        return new ResponseEntity<ProductInventory>(newProductInventory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> update(@Valid @PathVariable int id, @RequestBody ProductInventory productInventory) {
        service.update(id, productInventory);
        return new ResponseEntity<Integer>(id, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        service.deleteById(id);
    }

}
