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

import com.skillstorm.warehouse_management.models.Warehouse;
import com.skillstorm.warehouse_management.services.WarehouseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/warehouse")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://mystery-box-warehouses-frontend.s3-website-us-east-1.amazonaws.com"})
public class WarehouseController{
    
    private final Logger logger = LoggerFactory.getLogger(WarehouseController.class);
    private WarehouseService service;

    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> findAll() {
        List<Warehouse> warehouses = service.findAll();

        return new ResponseEntity<List<Warehouse>>(warehouses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> findById(@PathVariable int id) {
        Optional<Warehouse> warehouse = service.findById(id);
        if (warehouse.isPresent())
            return ResponseEntity.ok(warehouse.get());
        else 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Warehouse> create(@Valid @RequestBody Warehouse warehouse) {
        logger.debug("=== POST request to /warehouses with Warehouse of " + warehouse + " ===");
        Warehouse newWarehouse = service.save(warehouse);
        return new ResponseEntity<Warehouse>(newWarehouse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> update(@PathVariable int id, @RequestBody Warehouse warehouse) {
        service.update(id, warehouse);
        return new ResponseEntity<Integer>(id, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        service.deleteById(id);
    }

    
}