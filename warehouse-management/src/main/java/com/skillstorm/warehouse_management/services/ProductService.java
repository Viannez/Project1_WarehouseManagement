package com.skillstorm.warehouse_management.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.warehouse_management.models.Warehouse;
import com.skillstorm.warehouse_management.models.Product;
import com.skillstorm.warehouse_management.models.Category;

import com.skillstorm.warehouse_management.repositories.WarehouseRepository;
import com.skillstorm.warehouse_management.repositories.CategoryRepository;
import com.skillstorm.warehouse_management.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

    private ProductRepository repo;
    private WarehouseRepository wRepo;
    private CategoryRepository cRepo;

    // use dependency injection to get an instance of the ProductRepository
    public ProductService(ProductRepository repo, WarehouseRepository wRepo, CategoryRepository cRepo) {
        this.repo = repo;
        this.wRepo = wRepo;
        this.cRepo = cRepo;
    }

    public Iterable<Product> findAll() {
        return repo.findAll();
    }

    public Optional<Product> findById(int id) {
        return repo.findById(id);
    }

    @Transactional
    public Product save(Product product) {
        // Check if warehouse exists
        Warehouse warehouse = product.getWarehouse();
        if (wRepo.existsById(warehouse.getId())) {
            warehouse = wRepo.findById(warehouse.getId()).get();
            product.setWarehouse(warehouse);
        }
        // Check if category exists
        Category category = product.getCategory();
        if (wRepo.existsById(category.getId())) {
            category = cRepo.findById(category.getId()).get();
            product.setCategory(category);
        }
        return repo.save(product); 
    }

    public void update(int id, Product product) {
        if (!repo.existsById(id)) 
            throw new NoSuchElementException("Product with id " + id + " does not exist");
        product.setId(id);
        repo.save(product);
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }


}
