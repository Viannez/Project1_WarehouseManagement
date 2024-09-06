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

import com.skillstorm.warehouse_management.models.Category;
import com.skillstorm.warehouse_management.models.Product;
import com.skillstorm.warehouse_management.services.ProductService;



public class ProductControllerTest {
    //creates a mock instance of the ProductService class
    @Mock
    private ProductService productService;

    //injects the ProductService mock object into the ProductController
    @InjectMocks
    private ProductController productController;
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

    // Tests if the mocked product service successfully HTTP 200 OK after controller calls findAll() with expected values
    @Test
    public void findAllProductsTest() {
        //Given
        List<Product> expectedProducts = Arrays.asList(new Product(), new Product());

        //When (stubbing)
        when(productService.findAll())
        
        //Then
        .thenReturn(expectedProducts);

        ResponseEntity<List<Product>> response = productController.findAll();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody(), expectedProducts);
        Assert.assertEquals(response.getBody().size(), 2);
    }

    // Tests if the mocked product service successfully HTTP 200 OK after controller calls findById()
    // for a product id that does exist 
    @Test
    public void findProductByIdTest() {
        int productId = 1;
        Product inputProduct = new Product();
        Optional<Product> expectedProduct = Optional.of(inputProduct);
        
        when(productService.findById(productId))
        .thenReturn(expectedProduct);

        ResponseEntity<Product> response = productController.findById(productId);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    // Tests if the mocked product service gets HTTP 404 NOT FOUND after controller calls findById()
    // for a product id that doesn't exist 
    @Test
    public void findProductByIdInvalidTest() {
        int productId = 1;
        Product inputProduct = new Product();
        Optional<Product> expectedProduct = Optional.of(inputProduct);
        
        when(productService.findById(productId))
        .thenReturn(expectedProduct);

        ResponseEntity<Product> response = productController.findById(2);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    // Tests if the mocked product service successfully HTTP 201 CREATED after controller calls create() to create a product
    @Test
    public void createProductTest() {
        Category category = new Category();
        Product inputProduct = new Product();
        inputProduct.setCategory(category);
        Product savedProduct = new Product();
        savedProduct.setCategory(category);
        
        when(productService.save(inputProduct))
        .thenReturn(savedProduct);

        ResponseEntity<Product> response = productController.create(inputProduct);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    // Tests if the mocked product service successfully HTTP 200 OK after controller calls update()
    @Test
    public void updateProductTest() {
        Product inputProduct = new Product();
        when(productService.update(inputProduct.getId(), inputProduct))
        .thenReturn(inputProduct.getId());

        ResponseEntity<Integer> response = productController.update(inputProduct.getId(), inputProduct);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    // Tests if the mocked product service successfully deletes product with matching id after controller calls deleteById()
    @Test
    public void deleteProductTest() {
        Product inputProduct = new Product(1, "testName", 20);
        Optional<Product> expectedProduct = Optional.of(inputProduct);

        when(productService.findById(inputProduct.getId()))
        .thenReturn(expectedProduct);
        
        productService.deleteById(inputProduct.getId());
        verify(productService, times(1)).deleteById(inputProduct.getId());
    }

    
}
