package com.skillstorm.warehouse_management.services;

import java.util.Arrays;
import java.util.List;
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

import com.skillstorm.warehouse_management.models.Product;
import com.skillstorm.warehouse_management.models.ProductInventory;
import com.skillstorm.warehouse_management.models.Warehouse;
import com.skillstorm.warehouse_management.repositories.ProductInventoryRepository;
import com.skillstorm.warehouse_management.repositories.ProductRepository;
import com.skillstorm.warehouse_management.repositories.WarehouseRepository;



public class ProductInventoryServiceTest {
    //creates a mock instance of the ProductInventoryService class
    @Mock
    private ProductInventoryRepository productInventoryRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private WarehouseRepository warehouseRepository;

    //injects the ProductInventoryService mock object into the ProductInventoryController
    @InjectMocks
    private ProductInventoryService productInventoryService;
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
    public void findAllProductInventorysTest() {
        //Given
        List<ProductInventory> expectedProductInventorys = Arrays.asList(new ProductInventory(), new ProductInventory());

        //When (stubbing)
        when(productInventoryRepository.findAll())
        
        //Then
        .thenReturn(expectedProductInventorys);

        List<ProductInventory> response = productInventoryService.findAll();
        System.out.println(response);
        Assert.assertEquals(response, expectedProductInventorys);
    }

    @Test
    public void findProductInventoryByIdTest() {
        int productInventoryId = 1;
        ProductInventory inputProductInventory = new ProductInventory();
        Optional<ProductInventory> expectedProductInventory = Optional.of(inputProductInventory);
        
        when(productInventoryRepository.findById(productInventoryId))
        .thenReturn(expectedProductInventory);

        Optional<ProductInventory> response = productInventoryService.findById(productInventoryId);

        Assert.assertEquals(response, expectedProductInventory);
    }

    @Test
    public void createProductInventoryTest() {
        Product product = new Product();
        productRepository.save(product);

        Warehouse warehouse = new Warehouse();
        warehouseRepository.save(warehouse);

        ProductInventory inputProductInventory = new ProductInventory();
        inputProductInventory.setId(1);
        inputProductInventory.setProduct(product);
        inputProductInventory.setWarehouse(warehouse);

        when(productInventoryRepository.save(inputProductInventory))
        .thenReturn(inputProductInventory);

        ProductInventory savedProductInventory = productInventoryService.save(inputProductInventory);
        System.out.println(savedProductInventory);

        ProductInventory response = productInventoryService.save(inputProductInventory);

        Assert.assertEquals(response, savedProductInventory);
    }

    @Test
    public void updateProductInventoryTest() {
        Product product = new Product();
        productRepository.save(product);

        Warehouse warehouse = new Warehouse();
        warehouseRepository.save(warehouse);

        ProductInventory inputProductInventory = new ProductInventory();
        inputProductInventory.setId(1);
        inputProductInventory.setProduct(product);
        inputProductInventory.setWarehouse(warehouse);

        when(productInventoryRepository.save(inputProductInventory))
        .thenReturn(inputProductInventory);

        when(productInventoryRepository.existsById(inputProductInventory.getId()))
        .thenReturn(true);

        int response = productInventoryService.update(inputProductInventory.getId(), inputProductInventory);
        System.out.println(response);

        Assert.assertEquals(response, inputProductInventory.getId());
    }

    @Test
    public void deleteProductInventoryTest() {
        ProductInventory inputProductInventory = new ProductInventory();
        Optional<ProductInventory> expectedProductInventory = Optional.of(inputProductInventory);

        when(productInventoryRepository.findById(inputProductInventory.getId()))
        .thenReturn(expectedProductInventory);
        
        productInventoryRepository.deleteById(inputProductInventory.getId());
        verify(productInventoryRepository, times(1)).deleteById(inputProductInventory.getId());
    }

    
}
