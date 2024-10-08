package com.skillstorm.warehouse_management.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.warehouse_management.models.Category;
import com.skillstorm.warehouse_management.models.Product;
import com.skillstorm.warehouse_management.models.ProductInventory;
import com.skillstorm.warehouse_management.models.Warehouse;
import com.skillstorm.warehouse_management.repositories.ProductInventoryRepository;
import com.skillstorm.warehouse_management.repositories.ProductRepository;
import com.skillstorm.warehouse_management.repositories.WarehouseRepository;

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

    public List<ProductInventory> findAll() {
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
            Optional<Warehouse> w = wRepo.findById(warehouse.getId());
            if (!w.isPresent())
                return null;
            warehouse = w.get();
            productInventory.setWarehouse(warehouse);
        }
        // Check if product exists
        Product product = productInventory.getProduct();
        if (wRepo.existsById(product.getId())) {
            Optional<Product> p = pRepo.findById(product.getId());
            if (!p.isPresent())
                return null;
            product = p.get();
            productInventory.setProduct(product);
        }
        return repo.save(productInventory); 
    }

    @Transactional
    public int update(int id, ProductInventory productInventory) {
        if (!repo.existsById(id)) 
            throw new NoSuchElementException("ProductInventory with id " + id + " does not exist");
        productInventory.setId(id);
        repo.save(productInventory);
        return id;
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }


}
