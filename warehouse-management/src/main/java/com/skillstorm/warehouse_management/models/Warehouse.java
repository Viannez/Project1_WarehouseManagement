package com.skillstorm.warehouse_management.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "warehouse")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Warehouse.class)
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        columnDefinition = "SERIAL"
    )
    private int id;

    @Column(length=50)
    private String name;

    @Column(length=50)
    private String address;

    @Column
    @Min(value = 100)
    @Max(value = 1000)
    private int capacity;

    @OneToMany(mappedBy = "warehouse", targetEntity = Product.class) 
    @JsonBackReference 
    List<Product> products;

    public Warehouse(){}

    public Warehouse(int id, String name, String address, int capacity)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getInventory() {
        return (products==null) ? 0 : (products.stream().filter(product -> product.getStock() > 10).mapToInt(Product::getStock).sum());
    }

    public List<Product> getProduct() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Warehouse [id=" + id + 
        ", name=" + name + 
        ", address=" + address + 
        ", capacity=" +  this.getInventory() + "/" + capacity + 
        ", products=" + products
                + "]";
    }
}
