package com.skillstorm.warehouse_management.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.warehouse_management.models.Warehouse;
import com.skillstorm.warehouse_management.repositories.WarehouseRepository;

import jakarta.transaction.Transactional;

@Service
public class WarehouseService {

    private WarehouseRepository repo;

    public WarehouseService(WarehouseRepository repo) {
        this.repo = repo;
    }

    public Iterable<Warehouse> findAll() {
        return repo.findAll();
    }

    public Optional<Warehouse> findById(int id) {
        return repo.findById(id);
    }

    @Transactional
    public Warehouse save(Warehouse warehouse) {
        return repo.save(warehouse);
    }

    @Transactional
    public void update(int id, Warehouse warehouse) {
        if (!repo.existsById(id)) 
            throw new NoSuchElementException("Warehouse with id " + id + " does not exist");
        warehouse.setId(id);
        repo.save(warehouse);
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }

}
