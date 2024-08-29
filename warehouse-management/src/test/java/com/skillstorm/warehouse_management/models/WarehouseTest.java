package com.skillstorm.warehouse_management.models;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WarehouseTest {

    private Warehouse warehouse;
    private List<ProductInventory> productInventories;

    @BeforeMethod
    public void setUp() {
        warehouse = new Warehouse();

        ProductInventory product1 = new ProductInventory();
        product1.setId(1);
        product1.setStock(30);

        ProductInventory product2 = new ProductInventory();
        product2.setId(2);
        product2.setStock(20);

        productInventories = Arrays.asList(product1, product2);

        warehouse.setId(1);
        warehouse.setName("testName");
        warehouse.setAddress("testAddress");
        warehouse.setCapacity(300);
        warehouse.setProductInventories(productInventories);
    }
    @AfterTest
    public void teardown() throws Exception{
        warehouse = null;
    }

    @Test
    public void idGetterSetter() {
        assertEquals(warehouse.getId(), 1);
        warehouse.setId(2);
        assertEquals(warehouse.getId(), 2);
    }

    @Test
    public void nameGetterSetter() {
        assertEquals(warehouse.getName(), "testName");
        warehouse.setName("testName2");
        assertEquals(warehouse.getName(), "testName2");
    }
    
    @Test
    public void addressGetterSetter() {
        assertEquals(warehouse.getAddress(), "testAddress");
        warehouse.setAddress("testAddress2");
        assertEquals(warehouse.getAddress(), "testAddress2");
    }
    @Test
    public void capcityGetterSetter() {
        assertEquals(warehouse.getCapacity(), 300);
        warehouse.setCapacity(400);
        assertEquals(warehouse.getCapacity(), 400);
    }

    @Test
    public void productInventoriesGetterSetter() {
        assertEquals(warehouse.getProductInventories(), productInventories);

        ProductInventory productInventory = new ProductInventory();
        productInventory.setId(4);
        productInventory.setStock(13);

        List<ProductInventory> productInventories2 = Arrays.asList(productInventory);
        warehouse.setProductInventories(productInventories2);
        assertEquals(warehouse.getProductInventories(), productInventories2);
    }

    @Test
    public void InventoryCapacityGetter() {
        assertEquals(warehouse.getInventoryCapacity(), 50);
    }

    @Test
    public void testToString() {
        System.out.println(warehouse);
        String expectedToString = "Warehouse [id=1, name=testName, address=testAddress, capacity=50/300, productInventories=" +productInventories +"]";
        System.out.println(expectedToString);
        assertEquals(warehouse.toString(), expectedToString);
    }
}