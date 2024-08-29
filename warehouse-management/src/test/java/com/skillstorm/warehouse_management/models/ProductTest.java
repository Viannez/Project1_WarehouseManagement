package com.skillstorm.warehouse_management.models;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTest {

    private Product product;
    private Category category;
    private List<ProductInventory> productInventories;

    @BeforeMethod
    public void setUp() {
        product = new Product();
        
        category = new Category();
        category.setId(1);
        category.setName("testCategory");

        ProductInventory product1 = new ProductInventory();
        product1.setId(1);
        product1.setStock(30);

        ProductInventory product2 = new ProductInventory();
        product2.setId(2);
        product2.setStock(20);

        productInventories = Arrays.asList(product1, product2);

        product.setId(1);
        product.setName("testName");
        product.setPrice(12);
        product.setCategory(category);
        product.setProductInventories(productInventories);
    }
    @AfterTest
    public void teardown() throws Exception{
        product = null;
        category = null;
    }

    @Test
    public void idGetterSetter() {
        assertEquals(product.getId(), 1);
        product.setId(2);
        assertEquals(product.getId(), 2);
    }

    @Test
    public void nameGetterSetter() {
        assertEquals(product.getName(), "testName");
        product.setName("testName2");
        assertEquals(product.getName(), "testName2");
    }
    
    @Test
    public void categoryGetterSetter() {
        assertEquals(product.getCategory(), category);

        Category category2 = new Category();
        product.setId(4);

        product.setCategory(category2);

        assertEquals(product.getCategory(), category2);
    }
    @Test
    public void priceGetterSetter() {
        assertEquals(product.getPrice(), 12);
        product.setPrice(24);
        assertEquals(product.getPrice(), 24);
    }

    @Test
    public void productInventoriesGetterSetter() {
        assertEquals(product.getProductInventories(), productInventories);

        ProductInventory productInventory = new ProductInventory();
        productInventory.setId(4);
        productInventory.setStock(13);

        List<ProductInventory> productInventories2 = Arrays.asList(productInventory);
        product.setProductInventories(productInventories2);
        assertEquals(product.getProductInventories(), productInventories2);
    }


    @Test
    public void testToString() {
        System.out.println(product);
        String expectedToString = "Product [id=1, name=testName, price=12, categoryName=" + category.getName() + ", productInventories=" +productInventories+"]";
        System.out.println(expectedToString);
        assertEquals(product.toString(), expectedToString);
    }
}