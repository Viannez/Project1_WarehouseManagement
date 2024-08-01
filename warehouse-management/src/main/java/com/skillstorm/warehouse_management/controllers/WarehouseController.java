package com.skillstorm.warehouse_management.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.warehouse_management.models.Warehouse;
import com.skillstorm.warehouse_management.repositories.WarehouseRepository;
import com.skillstorm.warehouse_management.services.WarehouseService;

import jakarta.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/warehouse")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class WarehouseController{
    
    private final Logger logger = LoggerFactory.getLogger(WarehouseController.class);
    private WarehouseService service;

    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Warehouse> findAll() {
        return service.findAll();
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
    public Warehouse create(@Valid @RequestBody Warehouse warehouse) {
        logger.debug("=== POST request to /warehouses with Warehouse of " + warehouse + " ===");
        return service.save(warehouse);
    }

    @PutMapping("/{id}")
    public void putMethodName(@PathVariable int id, @RequestBody Warehouse warehouse) {
        service.update(id, warehouse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        service.deleteById(id);
    }

    
}