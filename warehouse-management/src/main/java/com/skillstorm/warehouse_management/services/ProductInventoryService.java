package com.skillstorm.warehouse_management.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.warehouse_management.models.Warehouse;
import com.skillstorm.warehouse_management.models.Product;
import com.skillstorm.warehouse_management.models.ProductInventory;

import com.skillstorm.warehouse_management.repositories.WarehouseRepository;
import com.skillstorm.warehouse_management.repositories.ProductRepository;
import com.skillstorm.warehouse_management.repositories.ProductInventoryRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductInventoryService {

    private ProductInventoryRepository repo;
    private WarehouseRepository wRepo;
    private ProductRepository pRepo;

    public ProductInventoryService(ProductInventoryRepository repo, WarehouseRepository wRepo, ProductRepository pRepo) {
        this.repo = repo;
        this.wRepo = wRepo;
        this.pRepo = pRepo;
    }

    public Iterable<ProductInventory> findAll() {
        return repo.findAll();
    }

    public Optional<ProductInventory> findById(int id) {
        return repo.findById(id);
    }

    @Transactional
    public ProductInventory save(ProductInventory productInventory) {
        // Check if warehouse exists
        Warehouse warehouse = productInventory.getWarehouse();
        if (wRepo.existsById(warehouse.getId())) {
            warehouse = wRepo.findById(warehouse.getId()).get();
            productInventory.setWarehouse(warehouse);
        }
        // Check if product exists
        Product product = productInventory.getProduct();
        if (wRepo.existsById(product.getId())) {
            product = pRepo.findById(product.getId()).get();
            productInventory.setProduct(product);
        }
        return repo.save(productInventory); 
    }

    public void update(int id, ProductInventory productInventory) {
        if (!repo.existsById(id)) 
            throw new NoSuchElementException("ProductInventory with id " + id + " does not exist");
        productInventory.setId(id);
        repo.save(productInventory);
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }


}
