package com.skillstorm.warehouse_management.models;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "product")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Product.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        columnDefinition = "SERIAL"
    ) 
    private int id;

    @Column(length=50)
    private String name;

    @Min(value = 0)
    private int price;

    @ManyToOne
    @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
    @NotNull
    @JoinColumn(name = "category_id")
    @JsonIdentityReference(alwaysAsId= true)
    private Category category;

    @OneToMany(mappedBy = "product", targetEntity = ProductInventory.class) 
    @JsonBackReference 
    List<ProductInventory> productInventories;

    public Product(){}

    public Product(int id, String name, int price)
    {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String setName() {
        return name;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductInventory> getProductInventories() {
        return productInventories;
    }

    public void setProductInventories(List<ProductInventory> productInventories) {
        this.productInventories = productInventories;
    }
    
    public int getInventory() {
        return (productInventories==null) ? 0 : (productInventories.stream().filter(product -> product.getStock() > 10).mapToInt(ProductInventory::getStock).sum());
    }


    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", category: " + (category == null ? null : category.getName()) + "]";
    }

    
}
