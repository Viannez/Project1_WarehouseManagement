package com.skillstorm.warehouse_management.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.warehouse_management.models.Category;
import com.skillstorm.warehouse_management.models.Product;
import com.skillstorm.warehouse_management.repositories.CategoryRepository;
import com.skillstorm.warehouse_management.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

    private ProductRepository repo;
    private CategoryRepository cRepo;

    public ProductService(ProductRepository repo, CategoryRepository cRepo) {
        this.repo = repo;
        this.cRepo = cRepo;
    }

    public List<Product> findAll() {
        return repo.findAll();
    }

    public Optional<Product> findById(int id) {
        return repo.findById(id);
    }

    @Transactional
    public Product save(Product product) {
        // Check if category exists
        Category category = product.getCategory();
        if (cRepo.existsById(category.getId())) {
            Optional<Category> c = cRepo.findById(category.getId());
        if (!c.isPresent())
            throw new NoSuchElementException("Category with id " + category.getId() + " does not exist");
        }
        return repo.save(product); 
    }

    @Transactional
    public int update(int id, Product product) {
        if (!repo.existsById(id)) 
            throw new NoSuchElementException("Product with id " + id + " does not exist");
        product.setId(id);
        repo.save(product);
        return id;
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }


}
