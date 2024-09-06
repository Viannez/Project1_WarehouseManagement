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
    private String warehouseDetailsUrl;
    // Setup cucumber ChromeOptions and instantiate a ChromeDriver and WarehouseDetailsPage
    @Before("@add-productinventory")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        options.addArguments("--headless", "--disable-dev-shm-usage", "--ignore-ssl-errors=yes", "'--ignore-certificate-errors'");
        this.driver = new ChromeDriver(options);
        this.warehouseDetailsPage = new WarehouseDetailsPage(driver);
    }
    // Tear down
    @After("@add-productinventory")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }
    
    // Navigate to the warehouse with matching name parameter
    @Given("ProductInventory: I am on the warehouse details page")
    public void iAmOnTheWarehouseDetailsPage() {
        this.warehouseDetailsUrl = this.warehouseDetailsPage.get("Greenish");
        this.warehouseDetailsPage.getUrl(warehouseDetailsUrl);
    }

    // Call method to simulate a click on the button to add a product to a warehouse
    @When("I click on the add product to warehouse button")
    public void iClickOnTheAddProductToWarehouseButton() {
        this.warehouseDetailsPage.clickAddProductToWarehouse();
    }

    // Checks if the form modal for adding a product to a warehouse is displayed
    @Then("I should see the add product to warehouse form modal")
    public void iShouldSeeTheAddProductToWarehouseFormModal() {
        assertTrue(this.warehouseDetailsPage.addProductToWarehouseModalDisplayed());
    }

    // Navigates to the "Greenish" warehouse, simulates a button click to add a product, and
    // checks if the form modal for adding a product to a warehouse is displayed 
    @Given("I have the add product to warehouse form modal open")
    public void iAmOnTheAddProductToWarehouseFormModal() {
        this.warehouseDetailsUrl = this.warehouseDetailsPage.get("Greenish");
        this.warehouseDetailsPage.getUrl(warehouseDetailsUrl);
        this.warehouseDetailsPage.clickAddProductToWarehouse();
        assertTrue(this.warehouseDetailsPage.addProductToWarehouseModalDisplayed());
    }

    // Checks if the add product to warehouse form is displayed
    // then sets the product name and product stock based on arguments
    @When("I enter valid product fields with name = {string} and stock = {string}")
    public void iEnterValidAddProductToWarehouseFormInputs(String productName, String productStock) {
        if(this.warehouseDetailsPage.addProductToWarehouseModalDisplayed()){
            // System.out.println("Add product to warehouse modal is displaying");
        }
        this.driver.switchTo().activeElement();
        this.warehouseDetailsPage.setProductName(productName);
        this.warehouseDetailsPage.setProductStock(productStock);
    }

    // Simulates a button click on submitting the add product to warehouse form
    @And("I click the add product to warehouse submit button")
    public void iClickTheAddProductToWarehouseSubmitButton() {
        this.warehouseDetailsPage.clickProductSubmitButton();
    }

    // Checks if the added product is stocked in the warehouse
    @Then("I should see the warehouse's newly added product in stock with name = {string} and stock = {string}")
    public void iShouldSeeTheAddedProductInTheWarehouse(String productName, String productStock) {
        this.driver.navigate().refresh();
        assertTrue(this.warehouseDetailsPage.addedProductIsDisplayed(productName, productStock));
    }
}