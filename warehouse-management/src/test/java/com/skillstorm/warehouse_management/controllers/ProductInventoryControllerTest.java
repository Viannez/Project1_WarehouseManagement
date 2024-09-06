package com.skillstorm.warehouse_management.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.skillstorm.warehouse_management.models.ProductInventory;
import com.skillstorm.warehouse_management.services.ProductInventoryService;




public class ProductInventoryControllerTest {
    //creates a mock instance of the ProductInventoryService class
    @Mock
    private ProductInventoryService productInventoryService;

    //injects the ProductInventoryService mock object into the ProductInventoryController
    @InjectMocks
    private ProductInventoryController productInventoryController;
    private AutoCloseable closeable;

    //initializes the mock objects to ensure they are ready before tests are run
    @BeforeTest
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    //closes the closeable resource to ensure that the mock objects are cleaned up properly - prevents memory leaks
    @AfterTest
    public void teardown() throws Exception{
        closeable.close();
    }

    // Tests if the mocked productInventory service successfully HTTP 200 OK after controller calls findAll() with expected values
    @Test
    public void findAllProductInventorysTest() {
        //Given
        List<ProductInventory> expectedProductInventorys = Arrays.asList(new ProductInventory(), new ProductInventory());

        //When (stubbing)
        when(productInventoryService.findAll())
        
        //Then
        .thenReturn(expectedProductInventorys);

        ResponseEntity<List<ProductInventory>> response = productInventoryController.findAll();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody(), expectedProductInventorys);
        Assert.assertEquals(response.getBody().size(), 2);
    }

    // Tests if the mocked productInventory service successfully HTTP 200 OK after controller calls findById()
    // for a productInventory id that does exist 
    @Test
    public void findProductInventoryByIdTest() {
        int productInventoryId = 1;
        ProductInventory inputProductInventory = new ProductInventory();
        Optional<ProductInventory> expectedProductInventory = Optional.of(inputProductInventory);
        
        when(productInventoryService.findById(productInventoryId))
        .thenReturn(expectedProductInventory);

        ResponseEntity<ProductInventory> response = productInventoryController.findById(productInventoryId);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    // Tests if the mocked productInventory service gets HTTP 404 NOT FOUND after controller calls findById()
    // for a product id that doesn't exist 
    @Test
    public void findProductInventoryByIdInvalidTest() {
        int productInventoryId = 1;
        ProductInventory inputProductInventory = new ProductInventory();
        inputProductInventory.setId(productInventoryId);
        Optional<ProductInventory> expectedProductInventory = Optional.of(inputProductInventory);
        
        when(productInventoryService.findById(1))
        .thenReturn(expectedProductInventory);

        ResponseEntity<ProductInventory> response = productInventoryController.findById(2);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    // Tests if the mocked productInventory service successfully HTTP 201 CREATED after controller calls create() to create a productInventory
    @Test
    public void createProductInventoryTest() {
        ProductInventory inputProductInventory = new ProductInventory();
        ProductInventory savedProductInventory = new ProductInventory();
        when(productInventoryService.save(inputProductInventory))
        .thenReturn(savedProductInventory);

        ResponseEntity<ProductInventory> response = productInventoryController.create(inputProductInventory);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    // Tests if the mocked productInventory service successfully HTTP 200 OK after controller calls update()
    @Test
    public void updateProductInventoryTest() {
        ProductInventory inputProductInventory = new ProductInventory();
        when(productInventoryService.update(inputProductInventory.getId(), inputProductInventory))
        .thenReturn(inputProductInventory.getId());

        ResponseEntity<Integer> response = productInventoryController.update(inputProductInventory.getId(), inputProductInventory);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    // Tests if the mocked productInventory service successfully deletes productInventory with matching id after controller calls deleteById()
    @Test
    public void deleteProductInventoryTest() {
        ProductInventory inputProductInventory = new ProductInventory();
        inputProductInventory.setId(1);
        Optional<ProductInventory> expectedProductInventory = Optional.of(inputProductInventory);

        when(productInventoryService.findById(inputProductInventory.getId()))
        .thenReturn(expectedProductInventory);
        
        productInventoryService.deleteById(inputProductInventory.getId());
        verify(productInventoryService, times(1)).deleteById(inputProductInventory.getId());
    }

    
}
