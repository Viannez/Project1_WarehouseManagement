package com.skillstorm.warehouse_management.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.warehouse_management.models.Category;
import com.skillstorm.warehouse_management.models.Product;
import com.skillstorm.warehouse_management.repositories.CategoryRepository;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://mystery-box-warehouses-frontend.s3-website-us-east-1.amazonaws.com"})
public class CategoryController{
    
    private CategoryRepository repo;

    public CategoryController(CategoryRepository repo){
        this.repo = repo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable int id) {
        Optional<Category> category = repo.findById(id);
        if (category.isPresent())
            return ResponseEntity.ok(category.get());
        else 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/products")
    public List<Product> findProductsByCategory(@PathVariable int id) {
        return repo.findById(id).get().getProduct();
    }

    @GetMapping()
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = repo.findAll();
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }
}