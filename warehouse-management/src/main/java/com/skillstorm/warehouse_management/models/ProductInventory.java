package com.skillstorm.warehouse_management.models;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "product_inventory", uniqueConstraints = { @UniqueConstraint(columnNames = { "warehouse_id", "product_id" }) })
public class ProductInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(
        columnDefinition = "SERIAL"
    )
    private int id;
    
    @Min(value = 0)
    private int stock;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "warehouse_id")
    @JsonIdentityReference(alwaysAsId= true)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_id")
    @JsonIdentityReference(alwaysAsId= true)
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Product Inventory [id=" + id + ", stock=" + stock + ", warehouse: " + (warehouse == null ? null : warehouse.getName()) + ", product: " + (product == null ? null : product.getName()) + "]";
    }

    
}
