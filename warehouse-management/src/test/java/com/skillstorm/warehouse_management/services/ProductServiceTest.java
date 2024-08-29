package com.skillstorm.warehouse_management.services;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.skillstorm.warehouse_management.models.Category;
import com.skillstorm.warehouse_management.models.Product;
import com.skillstorm.warehouse_management.repositories.CategoryRepository;
import com.skillstorm.warehouse_management.repositories.ProductRepository;



public class ProductServiceTest {
    //creates a mock instance of the ProductService class
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;

    //injects the ProductService mock object into the ProductController
    @InjectMocks
    private ProductService productService;
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
    public void findAllProductsTest() {
        //Given
        List<Product> expectedProducts = Arrays.asList(new Product(), new Product());

        //When (stubbing)
        when(productRepository.findAll())
        
        //Then
        .thenReturn(expectedProducts);

        List<Product> response = productService.findAll();

        Assert.assertEquals(response, expectedProducts);
    }

    @Test
    public void findProductByIdTest() {
        int productId = 1;
        Product inputProduct = new Product();
        Optional<Product> expectedProduct = Optional.of(inputProduct);
        
        when(productRepository.findById(productId))
        .thenReturn(expectedProduct);

        Optional<Product> response = productService.findById(productId);

        Assert.assertEquals(response, expectedProduct);
    }

    @Test
    public void createProductTest() {
        Category category = new Category();
        categoryRepository.save(category);

        Product inputProduct = new Product();
        inputProduct.setCategory(category);
        Product savedProduct = new Product();
        savedProduct.setCategory(category);

        when(productRepository.save(inputProduct))
        .thenReturn(savedProduct);

        Product response = productService.save(inputProduct);

        Assert.assertEquals(response, savedProduct);
    }

    @Test
    public void updateProductTest() {
        Category category = new Category();
        categoryRepository.save(category);

        Product inputProduct = new Product(1, "testName", 12);
        inputProduct.setCategory(category);

        Product savedProduct = new Product(1, "testName", 12);
        savedProduct.setCategory(category);

        when(productRepository.save(inputProduct))
        .thenReturn(savedProduct);

        when(productRepository.existsById(inputProduct.getId()))
        .thenReturn(true);
        
        System.out.println(savedProduct);

        int response = productService.update(inputProduct.getId(), inputProduct);

        Assert.assertEquals(response, inputProduct.getId());
    }

    @Test
    public void updateProductInvalidTest() {
        Category category = new Category();
        categoryRepository.save(category);

        Product inputProduct = new Product(1, "testName", 12);
        inputProduct.setCategory(category);

        Product savedProduct = new Product(1, "testName", 12);
        savedProduct.setCategory(category);

        when(productRepository.save(inputProduct))
        .thenReturn(savedProduct);

        when(productRepository.existsById(2))
        .thenReturn(false);
        
        Assert.assertThrows(NoSuchElementException.class, () -> {
            productService.update(2, inputProduct);
        });
    }

    @Test
    public void deleteProductTest() {
        Product inputProduct = new Product(1, "testName", 20);
        Optional<Product> expectedProduct = Optional.of(inputProduct);

        when(productRepository.findById(inputProduct.getId()))
        .thenReturn(expectedProduct);
        
        productRepository.deleteById(inputProduct.getId());
        verify(productRepository, times(1)).deleteById(inputProduct.getId());
    }

    
}
