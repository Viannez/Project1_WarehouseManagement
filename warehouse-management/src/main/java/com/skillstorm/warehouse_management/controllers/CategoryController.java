package com.skillstorm.warehouse_management.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.warehouse_management.models.Category;
import com.skillstorm.warehouse_management.models.Product;
import com.skillstorm.warehouse_management.repositories.CategoryRepository;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class CategoryController{
    
    private CategoryRepository repo;

    public CategoryController(CategoryRepository repo){
        this.repo = repo;
    }

    @GetMapping("/{id}")
    public Category findCategory(@PathVariable int id) {
        return repo.findById(id).get();
    }

    @GetMapping("/{id}/products")
    public List<Product> findProductsByCategory(@PathVariable int id) {
        return repo.findById(id).get().getProduct();
    }

    @GetMapping()
    public List<Category> findAll() {
        return repo.findAll();
    }
}