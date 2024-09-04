package com.skillstorm.cucumber.definitions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.skillstorm.selenium.WarehouseDetailsPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddProductInventorySteps {
    private WebDriver driver;
    private WarehouseDetailsPage warehouseDetailsPage;

    @Before("@add-productinventory")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        this.driver = new ChromeDriver(options);
        this.warehouseDetailsPage = new WarehouseDetailsPage(driver);
    }
    @After("@add-productinventory")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }
    
    //Get to modal
    @Given("ProductInventory: I am on the warehouse details page")
    public void iAmOnTheWarehouseDetailsPage() {
        this.warehouseDetailsPage.get();
    }

    @When("I click on the add product to warehouse button")
    public void iClickOnTheAddProductToWarehouseButton() {
        this.warehouseDetailsPage.clickAddProductToWarehouse();
    }

    @Then("I should see the add product to warehouse form modal")
    public void iShouldSeeTheAddProductToWarehouseFormModal() {
        assertTrue(this.warehouseDetailsPage.addProductToWarehouseModalDisplayed());
    }

    @Given("I have the add product to warehouse form modal open")
    public void iAmOnTheAddProductToWarehouseFormModal() {
        this.warehouseDetailsPage.get();
        this.warehouseDetailsPage.clickAddProductToWarehouse();
        assertTrue(this.warehouseDetailsPage.addProductToWarehouseModalDisplayed());
    }

    @When("I enter valid product fields with name = {string} and stock = {string}")
    public void iEnterValidAddProductToWarehouseFormInputs(String productName, String productStock) {
        if(this.warehouseDetailsPage.addProductToWarehouseModalDisplayed()){
            System.out.println("Add product to warehouse modal is displaying");

        }
        this.driver.switchTo().activeElement();
        this.warehouseDetailsPage.setProductName(productName);
        this.warehouseDetailsPage.setProductStock(productStock);
    }

    @And("I click the add product to warehouse submit button")
    public void iClickTheAddProductToWarehouseSubmitButton() {
        this.warehouseDetailsPage.clickProductSubmitButton();
    }

    @Then("I should see the warehouse's newly added product in stock with name = {string} and stock = {string}")
    public void iShouldSeeTheAddedProductInTheWarehouse(String productName, String productStock) {
        this.driver.navigate().refresh();
        assertTrue(this.warehouseDetailsPage.addedProductIsDisplayed(productName, productStock));
    }
}