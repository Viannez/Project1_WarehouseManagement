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

    @Before("@delete-productinventory")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        this.driver = new ChromeDriver(options);
        this.warehouseDetailsPage = new WarehouseDetailsPage(driver);
    }
    @After("@delete-productinventory")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }
    
    //Get to modal
    @Given("Delete ProductInventory: I am on the warehouse details page")
    public void iAmOnTheWarehouseDetailsPage() {
        this.warehouseDetailsPage.get();
    }

    @And("there exists a product in the warehouse named {string}")
    public void thereExistsAProductInTheWarehouse(String productName) {
        this.warehouseDetailsPage.isAProductStockedInTheWarehouse(productName);
    }

    @When("I click on the delete product from warehouse button for the product named {string}")
    public void iClickOnTheAddProductToWarehouseButton(String productName) {
        this.warehouseDetailsPage.clickDeleteProductFromWarehouseButton(productName);
    }

    @Then("I should no longer see a product named {string} for that warehouse")
    public void iShouldNoLongerSeeTheProductInTheWarehouse(String productName) {
        assertFalse(this.warehouseDetailsPage.isProductDeletedFromWarehouse(productName));
    }
}