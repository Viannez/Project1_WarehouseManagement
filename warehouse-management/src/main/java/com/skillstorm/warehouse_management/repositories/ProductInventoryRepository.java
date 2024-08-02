package com.skillstorm.warehouse_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.warehouse_management.models.ProductInventory;

public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Integer>{
    
}
