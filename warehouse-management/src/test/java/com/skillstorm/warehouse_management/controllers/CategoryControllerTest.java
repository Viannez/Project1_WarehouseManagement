package com.skillstorm.warehouse_management.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import com.skillstorm.warehouse_management.repositories.CategoryRepository;



public class CategoryControllerTest {

    //creates a mock instance of the Categorieservice class
    @Mock
    private CategoryRepository categoryRepository;

    //injects the Categorieservice mock object into the CategoryController
    @InjectMocks
    private CategoryController categoryController;
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

    // Tests if the mocked category repo successfully HTTP 200 OK after controller calls findAll() with expected values
    @Test
    public void findAllCategoriesTest() {
        //Given
        List<Category> expectedCategories = Arrays.asList(new Category(), new Category());

        //When (stubbing)
        when(categoryRepository.findAll())
        
        //Then
        .thenReturn(expectedCategories);

        ResponseEntity<List<Category>> response = categoryController.findAll();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody(), expectedCategories);
        Assert.assertEquals(response.getBody().size(), 2);
    }

    // Tests if the mocked category repo successfully HTTP 200 OK after controller calls findById()
    // for a category id that does exist 
    @Test
    public void findCategoryByIdTest() {
        Category inputCategory = new Category(1, "testname");
        Optional<Category> expectedCategory = Optional.of(inputCategory);
        
        when(categoryRepository.findById(inputCategory.getId()))
        .thenReturn(expectedCategory);

        ResponseEntity<Category> response = categoryController.findById(inputCategory.getId());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    // Tests if the mocked category repo gets HTTP 404 NOT FOUND after controller calls findById()
    // for a category id that doesn't exist 
    @Test
    public void findCategoryByIdInvalidTest() {
        Category inputCategory = new Category(1, "testname");
        Optional<Category> expectedCategory = Optional.of(inputCategory);
        
        when(categoryRepository.findById(1))
        .thenReturn(expectedCategory);

        ResponseEntity<Category> response = categoryController.findById(2);
        System.out.println(response);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    // Tests if the mocked category repo gets expected list of products in response
    // after controller calls findProductsByCategory()
    @Test
    public void findProductsByCategoryTest() {
        Category inputCategory = new Category(1, "testname");

        Product product1 = new Product();
        product1.setId(1);
        product1.setCategory(inputCategory);

        Product product2 = new Product();
        product2.setId(2);
        product2.setCategory(inputCategory);

        List<Product>products = Arrays.asList(product1, product2);
        inputCategory.setProducts(products);
        Optional<Category> expectedCategory = Optional.of(inputCategory);
        
        when(categoryRepository.findById(inputCategory.getId()))
        .thenReturn(expectedCategory);

        List<Product> response = categoryController.findProductsByCategory(inputCategory.getId());
        System.out.println(response);
        Assert.assertEquals(response, products);
    }
    
}
