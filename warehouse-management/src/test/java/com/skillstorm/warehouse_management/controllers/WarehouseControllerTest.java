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

import com.skillstorm.warehouse_management.models.Warehouse;
import com.skillstorm.warehouse_management.services.WarehouseService;


public class WarehouseControllerTest {

    //creates a mock instance of the WarehouseService class
    @Mock
    private WarehouseService warehouseService;

    //injects the WarehouseService mock object into the WarehouseController
    @InjectMocks
    private WarehouseController warehouseController;
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
        when(warehouseService.findAll())
        
        //Then
        .thenReturn(expectedWarehouses);

        ResponseEntity<List<Warehouse>> response = warehouseController.findAll();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody(), expectedWarehouses);
        Assert.assertEquals(response.getBody().size(), 2);
    }

    @Test
    public void findWarehouseByIdTest() {
        int warehouseId = 1;
        Warehouse inputWarehouse = new Warehouse();
        Optional<Warehouse> expectedWarehouse = Optional.of(inputWarehouse);
        
        when(warehouseService.findById(warehouseId))
        .thenReturn(expectedWarehouse);

        ResponseEntity<Warehouse> response = warehouseController.findById(warehouseId);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void createWarehouseTest() {
        Warehouse inputWarehouse = new Warehouse();
        Warehouse savedWarehouse = new Warehouse();
        when(warehouseService.save(inputWarehouse))
        .thenReturn(savedWarehouse);

        ResponseEntity<Warehouse> response = warehouseController.create(inputWarehouse);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void updateWarehouseTest() {
        Warehouse inputWarehouse = new Warehouse();
        when(warehouseService.update(inputWarehouse.getId(), inputWarehouse))
        .thenReturn(inputWarehouse.getId());

        ResponseEntity<Integer> response = warehouseController.update(inputWarehouse.getId(), inputWarehouse);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void deleteWarehouseTest() {
        Warehouse inputWarehouse = new Warehouse(1, "testName", "testAddress", 300);
        Optional<Warehouse> expectedWarehouse = Optional.of(inputWarehouse);

        when(warehouseService.findById(inputWarehouse.getId()))
        .thenReturn(expectedWarehouse);
        
        warehouseService.deleteById(inputWarehouse.getId());
        verify(warehouseService, times(1)).deleteById(inputWarehouse.getId());
    }

    
}
