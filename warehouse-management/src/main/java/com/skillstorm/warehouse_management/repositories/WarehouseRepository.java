package com.skillstorm.warehouse_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.warehouse_management.models.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

}
