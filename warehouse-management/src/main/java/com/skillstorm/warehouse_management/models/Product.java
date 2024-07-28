package com.skillstorm.warehouse_management.models;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        columnDefinition = "SERIAL"
    ) 
    private int id;

    @Min(value = 0)
    private int stock;

    @Min(value = 0)
    private int price;

    @ManyToOne
    @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
    @NotNull
    @JoinColumn(name = "warehouse_id")
    @JsonIdentityReference(alwaysAsId= true)
    private Warehouse warehouse;

    @ManyToOne
    @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
    @NotNull
    @JoinColumn(name = "category_id")
    @JsonIdentityReference(alwaysAsId= true)
    private Category category;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", stock=" + stock + ", price=" + price + ", warehouse: " + (warehouse == null ? null : warehouse.getName()) + ", category: " + (category == null ? null : category.getName()) + "]";
    }

    
}
