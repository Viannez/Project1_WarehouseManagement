package com.skillstorm.warehouse_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.warehouse_management.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    
}
