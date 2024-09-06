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

public class UpdateProductInventorySteps {
    private WebDriver driver;
    private WarehouseDetailsPage warehouseDetailsPage;

    @Before("@update-productinventory")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        options.addArguments("--headless", "--disable-dev-shm-usage", "--ignore-ssl-errors=yes", "'--ignore-certificate-errors'");
        this.driver = new ChromeDriver(options);


        this.warehouseDetailsPage = new WarehouseDetailsPage(driver);
    }
    @After("@update-productinventory")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }


    //Get to modal
    @Given("Update Product Inventory: I am on the warehouse {string} details page")
    public void iAmOnTheWarehouseDetailsPage(String warehouseName) {
        this.warehouseDetailsPage.get("Electronics Galore");
    }

    @When("I click on the update product inventory button ")
    public void iClickOnTheUpdateProductInventoryButton() {
        this.warehouseDetailsPage.clickUpdateProduct("miscellaneous");
    }

    @Then("I should see the update product inventory form modal")
    public void iShouldSeeTheUpdateProductInventoryFormModal() {
        assertTrue(this.warehouseDetailsPage.updateProductStockModalDisplayed());
    }

    //Update product valid
    @Given(" I have the update product inventory {string} form modal open")
    public void iAmOnTheUpdateProductInventoryFormModal(String productInventoryName) {
        this.warehouseDetailsPage.get("Electronics Galore");
        this.warehouseDetailsPage.clickUpdateProduct(productInventoryName);
        assertTrue(this.warehouseDetailsPage.updateProductStockModalDisplayed());
    }

    @When("I enter valid update fields with stock = {string}")
    public void iEnterValidFormInputs(String updateStock) {
        if(this.warehouseDetailsPage.updateProductStockModalDisplayed()){
            System.out.println("Update productInventory modal is displaying");

        }
        this.driver.switchTo().activeElement();
        this.warehouseDetailsPage.updateProductStock(updateStock);
    }

    @And("I click the update product inventory form submit button")
    public void iClickTheSubmitButton() {
        this.warehouseDetailsPage.clickSubmitButton();
    }

    @Then("I should see the product inventory {string} updated with stock = {string}")
    public void iShouldSeeACardWithMatchingInputs(String productName, String productStock) {
        this.driver.navigate().refresh();
        assertTrue(this.warehouseDetailsPage.productIsDisplayed(productName, productStock));
    }
}
