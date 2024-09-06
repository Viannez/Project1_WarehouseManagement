package com.skillstorm.cucumber.definitions;

import static org.junit.jupiter.api.Assertions.assertFalse;
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

public class DeleteProductInventorySteps {
    private WebDriver driver;
    private WarehouseDetailsPage warehouseDetailsPage;

    // Setup cucumber ChromeOptions and instantiate a ChromeDriver and WarehouseDetailsPage
    @Before("@delete-productinventory")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        options.addArguments("--headless", "--disable-dev-shm-usage", "--ignore-ssl-errors=yes", "'--ignore-certificate-errors'");

        this.driver = new ChromeDriver(options);
        
        this.warehouseDetailsPage = new WarehouseDetailsPage(driver);
    }
    // Tear down
    @After("@delete-productinventory")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }
    
    // Navigate to the warehouse with matching name parameter
    @Given("Delete ProductInventory: I am on the warehouse details page")
    public void iAmOnTheWarehouseDetailsPage() {
        this.warehouseDetailsPage.get("Greenish");
    }

    // Checks if the product specified is stocked in the warehouse
    @And("there exists a product in the warehouse named {string}")
    public void thereExistsAProductInTheWarehouse(String productName) {
        this.warehouseDetailsPage.isAProductStockedInTheWarehouse(productName);
    }

    // Call method to simulate a click on the button to delete a product from a warehouse
    @When("I click on the delete product from warehouse button for the product named {string}")
    public void iClickOnTheAddProductToWarehouseButton(String productName) {
        this.warehouseDetailsPage.clickDeleteProductFromWarehouseButton(productName);
    }

    // Checks if the deleted product is no longer stocked in the warehouse
    @Then("I should no longer see a product named {string} for that warehouse")
    public void iShouldNoLongerSeeTheProductInTheWarehouse(String productName) {
        assertFalse(this.warehouseDetailsPage.isProductDeletedFromWarehouse(productName));
    }
}