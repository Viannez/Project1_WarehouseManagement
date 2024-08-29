package com.skillstorm.warehouse_management.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.skillstorm.warehouse_management.models.Warehouse;
import com.skillstorm.warehouse_management.repositories.WarehouseRepository;

public class WarehouseServiceTest {
    
    @Mock
    private WarehouseRepository warehouseRepo;

    @InjectMocks
    private WarehouseService warehouseService;
    private AutoCloseable closeable;

    @BeforeTest
    public void setup(){
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterTest
    public void teardown() throws Exception{
        closeable.close();
    }

    @Test
    public void findAllWarehousesTest(){
        List<Warehouse> expectedWarehouses = Arrays.asList(new Warehouse(), new Warehouse());
        when(warehouseRepo.findAll()).thenReturn(expectedWarehouses);

        Iterable<Warehouse> response = warehouseService.findAll();
        Assert.assertEquals(response, expectedWarehouses);
    }

    @Test
    public void findWarehouseByIdTest(){
        int warehouseId = 1;
        Warehouse inputWarehouse = new Warehouse();
        Optional<Warehouse> expectedWarehouse = Optional.of(inputWarehouse);
        when(warehouseRepo.findById(warehouseId)).thenReturn(expectedWarehouse);

        Optional<Warehouse> response = warehouseService.findById(warehouseId);

        Assert.assertEquals(response, expectedWarehouse);
    }

    @Test
    public void saveWarehouseTest(){
        Warehouse inputWarehouse = new Warehouse();
        Warehouse savedWarehouse = new Warehouse();

        when(warehouseRepo.save(inputWarehouse)).thenReturn(savedWarehouse);

        Warehouse response = warehouseService.save(inputWarehouse);

        Assert.assertEquals(response, savedWarehouse);
    }
    
    @Test
    public void deleteWarehouseTest(){
        Warehouse inputWarehouse = new Warehouse(1, "warehouseToDelete", "testAddress", 300);
        Optional<Warehouse> expectedWarehouse = Optional.of(inputWarehouse);

        when(warehouseRepo.findById(inputWarehouse.getId())).thenReturn(expectedWarehouse);

        warehouseRepo.deleteById(inputWarehouse.getId());
        verify(warehouseRepo, times(1)).deleteById(inputWarehouse.getId());
    }
}
