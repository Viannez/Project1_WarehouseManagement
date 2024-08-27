package com.skillstorm.warehouse_management.controllers;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.skillstorm.warehouse_management.services.WarehouseService;

public class WarehouseControllerTest {

    //create mock instance of WarehouseService
    @Mock
    private WarehouseService warehouseService;

    //injects the MovieService mock object into the MovieController
    @InjectMocks
    private WarehouseController warehouseController;
    private AutoCloseable closeable;

    //initializes mocks objects to ensure they were ready before tests are run
    @BeforeTestClass
    public void setup(){
        closeable = MockitoAnnotations.openMocks(this);
    }
    @AfterTest
    public void teardown() throws Exception{
        closeable.close();
    }

    @Test
    public void findALlWarehouses() throws Exception{
        
    }


}
