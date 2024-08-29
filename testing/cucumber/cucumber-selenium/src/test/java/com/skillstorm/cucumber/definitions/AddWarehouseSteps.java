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

    @Before("@add-warehouse")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        this.driver = new ChromeDriver(options);
        this.warehousePage = new WarehousePage(driver);
    }
    @After("@add-warehouse")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }

    //Get to modal
    @Given("I am on the warehouses page")
    public void iAmOnTheWarehousesPage() {
        this.warehousePage.get();
    }

    @When("I click on the add warehouse button")
    public void iClickOnTheAddWarehouseButton() {
        this.warehousePage.clickAddWarehouse();
    }

    @Then("I should see the add warehouse form modal")
    public void iShouldSeeTheAddWarehouseFormModal() {
        assertTrue(this.warehousePage.addWarehouseModalDisplayed());
    }

    //Create and add warehouse
    @Given("I have warehouse modal open")
    public void iAmOnTheAddProducyModal() {
        this.warehousePage.get();
        this.warehousePage.clickAddWarehouse();
        assertTrue(this.warehousePage.addWarehouseModalDisplayed());
    }

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

    @And("I click the warehouse form submit button")
    public void iClickTheSubmitButton() {
        this.warehousePage.clickSubmitButton();
    }

    @Then("I should see warehouse a card with matching {string} and {string} and {string}")
    public void iShouldSeeACardWithMatchingInputs(String name, String address, String capacity) {
        this.driver.navigate().refresh();
        assertTrue(this.warehousePage.warehouseCardIsDisplayed(name, address, capacity));
    }

}
