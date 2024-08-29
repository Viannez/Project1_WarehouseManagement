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

    @Test
    public void findCategoryByIdTest() {
        Category inputCategory = new Category(1, "testname");
        Optional<Category> expectedCategory = Optional.of(inputCategory);
        
        when(categoryRepository.findById(inputCategory.getId()))
        .thenReturn(expectedCategory);

        ResponseEntity<Category> response = categoryController.findById(inputCategory.getId());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    
}
