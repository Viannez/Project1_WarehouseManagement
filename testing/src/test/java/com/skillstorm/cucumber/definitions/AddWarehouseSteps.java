package com.skillstorm.cucumber.definitions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.skillstorm.selenium.WarehousePage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddWarehouseSteps {
    private WebDriver driver;
    private WarehousePage warehousePage;

    // Setup cucumber ChromeOptions and instantiate a ChromeDriver and WarehousePage
    @Before("@add-warehouse")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        options.addArguments("--headless", "--disable-dev-shm-usage", "--ignore-ssl-errors=yes", "'--ignore-certificate-errors'");

        this.driver = new ChromeDriver(options);
        
        this.warehousePage = new WarehousePage(driver);
    }
    // Tear down
    @After("@add-warehouse")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }

    // Navigates to the /warehouse page
    @Given("I am on the warehouses page")
    public void iAmOnTheWarehousesPage() {
        this.warehousePage.get();
    }

    // Simulates a button click to add a warehouse
    @When("I click on the add warehouse button")
    public void iClickOnTheAddWarehouseButton() {
        this.warehousePage.clickAddWarehouse();
    }

    // Checks if the add warehouse form modal is displayed
    @Then("I should see the add warehouse form modal")
    public void iShouldSeeTheAddWarehouseFormModal() {
        assertTrue(this.warehousePage.addWarehouseModalDisplayed());
    }

    // Navigates to /warehouse page, simulates a button click to add a warehouse
    // and checks if the add warehouse form is displayed
    @Given("I have warehouse modal open")
    public void iAmOnTheAddProducyModal() {
        this.warehousePage.get();
        this.warehousePage.clickAddWarehouse();
        assertTrue(this.warehousePage.addWarehouseModalDisplayed());
    }

    // Checks if the add warehouse form is displayed
    // and sets the warehouse's name, address, and capacity based on arguments
    @When("I enter valid {string} and {string} and {string}")
    public void iEnterValidFormInputs(String name, String address, String capacity) {
        if(this.warehousePage.addWarehouseModalDisplayed()){
            System.out.println("Modal is displaying");

        }
        this.driver.switchTo().activeElement();
        this.warehousePage.setName(name);
        this.warehousePage.setAddress(address);
        this.warehousePage.setCapacity(capacity);
    }

    // Simulates clicking the submit button on the add warehouse form
    @And("I click the warehouse form submit button")
    public void iClickTheSubmitButton() {
        this.warehousePage.clickSubmitButton();
    }

    // Checks if the added product is displayed on the /warehouse page
    @Then("I should see warehouse a card with matching {string} and {string} and {string}")
    public void iShouldSeeACardWithMatchingInputs(String name, String address, String capacity) {
        this.driver.navigate().refresh();
        assertTrue(this.warehousePage.warehouseCardIsDisplayed(name, address, capacity));
    }

}
