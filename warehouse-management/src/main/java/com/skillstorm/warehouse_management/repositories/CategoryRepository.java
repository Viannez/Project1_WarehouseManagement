package com.skillstorm.warehouse_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.warehouse_management.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
