package com.skillstorm.warehouse_management.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductInventoryTest {

    private ProductInventory productInventory;
    private Product product;
    private Warehouse warehouse;

    @BeforeMethod
    public void setUp() {
        productInventory = new ProductInventory();
        
        product = new Product();
        product.setId(1);
        product.setName("testProduct");

        warehouse = new Warehouse();
        warehouse.setId(1);
        warehouse.setName("testWarehouse");

        productInventory.setId(1);
        productInventory.setProduct(product);
        productInventory.setWarehouse(warehouse);
        productInventory.setStock(30);
    }
    @AfterTest
    public void teardown() throws Exception{
        productInventory = null;
        product = null;
    }

    @Test
    public void idGetterSetter() {
        assertEquals(productInventory.getId(), 1);
        productInventory.setId(2);
        assertEquals(productInventory.getId(), 2);
    }

    @Test
    public void stockGetterSetter() {
        assertEquals(productInventory.getStock(), 30);
        productInventory.setStock(12);
        assertEquals(productInventory.getStock(), 12);
    }
    
    @Test
    public void productGetterSetter() {
        assertEquals(productInventory.getProduct(), product);

        Product product2 = new Product();
        product2.setId(4);
        product2.setName("testProduct2");

        productInventory.setProduct(product2);

        assertEquals(productInventory.getProduct(), product2);
    }

    @Test
    public void warehouseGetterSetter() {
        assertEquals(productInventory.getProduct(), product);

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setId(4);
        warehouse2.setName("testWarehouse2");

        productInventory.setWarehouse(warehouse2);

        assertEquals(productInventory.getWarehouse(), warehouse2);
    }

    @Test
    public void testToString() {
        System.out.println(productInventory);
        String expectedToString = "Product Inventory [id=1, stock=30, warehouseName=" +warehouse.getName() + ", productName=" + product.getName() + "]";
        System.out.println(expectedToString);
        assertEquals(productInventory.toString(), expectedToString);
    }
}