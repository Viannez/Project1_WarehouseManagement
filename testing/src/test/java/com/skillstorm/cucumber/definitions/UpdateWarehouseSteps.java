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

public class UpdateWarehouseSteps {
    private WebDriver driver;
    private WarehouseDetailsPage warehouseDetailsPage;
    private String warehouseDetailsUrl;

    // Setup cucumber ChromeOptions and instantiate a ChromeDriver and WarehouseDetailsPage
    @Before("@update-warehouse")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        options.addArguments("--headless", "--disable-dev-shm-usage", "--ignore-ssl-errors=yes", "'--ignore-certificate-errors'");

        this.driver = new ChromeDriver(options);
        
        this.warehouseDetailsPage = new WarehouseDetailsPage(driver);
    }
    // Tear down
    @After("@update-warehouse")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }
    
    // Navigate to the warehouse with matching name parameter
    @Given("I am on the warehouse {string} details page")
    public void iAmOnTheWarehouseDetailsPage(String warehouseName) {
        this.warehouseDetailsUrl = this.warehouseDetailsPage.get(warehouseName);
        this.warehouseDetailsPage.getUrl(warehouseDetailsUrl);
    }

    // Call method to simulate a click on the button to update a warehouse
    @When("I click on the update warehouse button")
    public void iClickOnTheUpdateWarehouseButton() {
        this.warehouseDetailsPage.clickUpdateWarehouse();
    }

    // Checks if the update warehouse form is displayed
    @Then("I should see the update warehouse form modal")
    public void iShouldSeeTheUpdateWarehouseFormModal() {
        assertTrue(this.warehouseDetailsPage.updateWarehouseModalDisplayed());
    }

    // Navigates to the warehouse with matching name, simulates a button click to update that warehouse
    // and checks if the update warehouse form is displayed
    @Given("I have the update warehouse {string} form modal open")
    public void iAmOnTheUpdateWarehouseFormModal(String warehouseName) {
        this.warehouseDetailsUrl = this.warehouseDetailsPage.get(warehouseName);
        this.warehouseDetailsPage.getUrl(warehouseDetailsUrl);
        this.warehouseDetailsPage.clickUpdateWarehouse();
        assertTrue(this.warehouseDetailsPage.updateWarehouseModalDisplayed());
    }

    // Checks if the update warehouse form is displayed
    // and sets the warehouse's updated name, address, and category
    @When("I enter valid update fields with name = {string}, address = {string}, capacity = {string}")
    public void iEnterValidFormInputs(String updatedName, String updatedAddress, String updatedCapacity) {
        if(this.warehouseDetailsPage.updateWarehouseModalDisplayed()){
            // System.out.println("Update warehouse modal is displaying");

        }
        this.driver.switchTo().activeElement();
        this.warehouseDetailsPage.setName(updatedName);
        this.warehouseDetailsPage.setAddress(updatedAddress);
        this.warehouseDetailsPage.setCapacity(updatedCapacity);
    }

    // Simulates a button click to submit the update warehouse form
    @And("I click the update warehouse form submit button")
    public void iClickTheSubmitButton() {
        this.warehouseDetailsPage.clickSubmitButton();
    }

    // Checks if the warehouse was updated according to the update warehouse form
    @Then("I should see the warehouse updated with name = {string}, address = {string}, capacity = {string}")
    public void iShouldSeeACardWithMatchingInputs(String updatedName, String updatedAddress, String updatedCapacity) {
        this.driver.navigate().refresh();
        assertTrue(this.warehouseDetailsPage.updatedWarehouseIsDisplayed(updatedName, updatedAddress, updatedCapacity));
    }
}
