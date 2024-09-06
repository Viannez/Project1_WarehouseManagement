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

    //injects the CategoryRepository mock object into the CategoryController
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

    //find all categories
    @Test
    public void findAllCategoriesTest() {
        //Given expected categories
        List<Category> expectedCategories = Arrays.asList(new Category(), new Category());

        //When (stubbing) findall method
        when(categoryRepository.findAll())
        
        //Then return expected
        .thenReturn(expectedCategories);

        //response from controller
        ResponseEntity<List<Category>> response = categoryController.findAll();

        //check the response 
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody(), expectedCategories);
        Assert.assertEquals(response.getBody().size(), 2);
    }
    
    //find by category id
    @Test
    public void findCategoryByIdTest() {

        //Given input and expected category
        Category inputCategory = new Category(1, "testname");
        Optional<Category> expectedCategory = Optional.of(inputCategory);
        
        //When stubbing findbyid method
        when(categoryRepository.findById(inputCategory.getId()))
        .thenReturn(expectedCategory);

        //response
        ResponseEntity<Category> response = categoryController.findById(inputCategory.getId());

        //check response
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    //test invalid fid by categoryid
    @Test
    public void findCategoryByIdInvalidTest() {
        Category inputCategory = new Category(1, "testname");
        Optional<Category> expectedCategory = Optional.of(inputCategory);
        
        //
        when(categoryRepository.findById(1))
        .thenReturn(expectedCategory);

        ResponseEntity<Category> response = categoryController.findById(2);
        System.out.println(response);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

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
