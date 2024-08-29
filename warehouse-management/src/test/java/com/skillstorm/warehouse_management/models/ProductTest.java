package com.skillstorm.warehouse_management.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTest {

    private Product product;
    private Category category;

    @BeforeMethod
    public void setUp() {
        product = new Product();
        
        category = new Category();
        category.setId(1);
        category.setName("testCategory");

        product.setId(1);
        product.setName("testName");
        product.setPrice(12);
        product.setCategory(category);
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
    public void testToString() {
        System.out.println(product);
        String expectedToString = "Product [id=1, name=testName, price=12, category=" + category + "]";
        System.out.println(expectedToString);
        assertEquals(product.toString(), expectedToString);
    }
}