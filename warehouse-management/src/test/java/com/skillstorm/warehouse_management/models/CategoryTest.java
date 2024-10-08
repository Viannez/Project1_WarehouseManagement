package com.skillstorm.warehouse_management.models;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CategoryTest {

    private Category category;
    private List<Product> products;

    // Set up instance of Category with testing values for its properties
    // and sets the instance of Product into a list of products
    @BeforeMethod
    public void setUp() {
        category = new Category();

        Product product1 = new Product();
        product1.setId(1);
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setId(2);
        product2.setCategory(category);

        products = Arrays.asList(product1, product2);

        category.setId(1);
        category.setName("testName");
        category.setProducts(products);
    }
    // Tear down after tests
    @AfterTest
    public void teardown() throws Exception{
        category = null;
        products = null;
    }

    // The below methods assert that the category's getters & setters are successfully retrieved and set
    // for the following properties: id, name, & list of products
    @Test
    public void idGetterSetter() {
        assertEquals(category.getId(), 1);
        category.setId(2);
        assertEquals(category.getId(), 2);
    }

    @Test
    public void nameGetterSetter() {
        assertEquals(category.getName(), "testName");
        category.setName("testName2");
        assertEquals(category.getName(), "testName2");
    }
    
    @Test
    public void productsGetterSetter() {
        assertEquals(category.getProduct(), products);

        Product product = new Product();
        product.setId(4);
        product.setCategory(category);

        List<Product> products2 = Arrays.asList(product);
        category.setProducts(products2);
        assertEquals(category.getProduct(), products2);
    }

    // Tests .toString() for our test Category instance
    @Test
    public void testToString() {
        String expectedToString = "Category [id=1, name=testName, products=" + products + "]";
        assertEquals(category.toString(), expectedToString);
    }
}