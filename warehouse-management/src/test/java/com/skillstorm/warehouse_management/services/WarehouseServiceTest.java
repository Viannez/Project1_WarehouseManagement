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

import com.skillstorm.warehouse_management.models.Warehouse;
import com.skillstorm.warehouse_management.repositories.WarehouseRepository;



public class WarehouseServiceTest {
    //creates a mock instance of the WarehouseService class
    @Mock
    private WarehouseRepository warehouseRepository;

    //injects the WarehouseService mock object into the WarehouseController
    @InjectMocks
    private WarehouseService warehouseService;
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
    public void findAllWarehousesTest() {
        //Given
        List<Warehouse> expectedWarehouses = Arrays.asList(new Warehouse(), new Warehouse());

        //When (stubbing)
        when(warehouseRepository.findAll())
        
        //Then
        .thenReturn(expectedWarehouses);

        List<Warehouse> response = warehouseService.findAll();

        Assert.assertEquals(response, expectedWarehouses);
    }

    @Test
    public void findWarehouseByIdTest() {
        int warehouseId = 1;
        Warehouse inputWarehouse = new Warehouse();
        Optional<Warehouse> expectedWarehouse = Optional.of(inputWarehouse);
        
        when(warehouseRepository.findById(warehouseId))
        .thenReturn(expectedWarehouse);

        Optional<Warehouse> response = warehouseService.findById(warehouseId);

        Assert.assertEquals(response, expectedWarehouse);
    }

    @Test
    public void createWarehouseTest() {
        Warehouse inputWarehouse = new Warehouse();

        when(warehouseRepository.save(inputWarehouse))
        .thenReturn(inputWarehouse);

        Warehouse response = warehouseService.save(inputWarehouse);

        Assert.assertEquals(response, inputWarehouse);
    }

    @Test
    public void updateWarehouseTest() {
        Warehouse inputWarehouse = new Warehouse(1, "testName", "testAddress", 300);
        when(warehouseRepository.save(inputWarehouse))
        .thenReturn(inputWarehouse);

        when(warehouseRepository.existsById(inputWarehouse.getId()))
        .thenReturn(true);
        
        // System.out.println(inputWarehouse);

        int response = warehouseService.update(inputWarehouse.getId(), inputWarehouse);

        Assert.assertEquals(response, inputWarehouse.getId());
    }

    @Test
    public void updateWarehouseInvalidTest() {
        Warehouse inputWarehouse = new Warehouse(1, "testName", "testAddress", 300);
        when(warehouseRepository.save(inputWarehouse))
        .thenReturn(inputWarehouse);

        when(warehouseRepository.existsById(2))
        .thenReturn(false);
        
        Assert.assertThrows(NoSuchElementException.class, () -> {
            warehouseService.update(2, inputWarehouse);
        });
    }

    @Test
    public void deleteWarehouseTest() {
        Warehouse inputWarehouse = new Warehouse(1, "testName", "testAddress", 300);
        Optional<Warehouse> expectedWarehouse = Optional.of(inputWarehouse);

        when(warehouseRepository.findById(inputWarehouse.getId()))
        .thenReturn(expectedWarehouse);
        
        warehouseRepository.deleteById(inputWarehouse.getId());
        verify(warehouseRepository, times(1)).deleteById(inputWarehouse.getId());
    }

    
}
