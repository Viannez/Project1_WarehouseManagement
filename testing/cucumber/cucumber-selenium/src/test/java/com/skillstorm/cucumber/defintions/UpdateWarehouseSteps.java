package com.skillstorm.cucumber.defintions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.skillstorm.selenium.WarehouseDetailsPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateWarehouseSteps {
    private WebDriver driver;
    private WarehouseDetailsPage warehouseDetailsPage;

    @Before("@update-warehouse")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        this.driver = new ChromeDriver(options);
        this.warehouseDetailsPage = new WarehouseDetailsPage(driver);
    }
    @After("@update-warehouse")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }

    //Get to modal
    @Given("I am on the warehouse details page")
    public void iAmOnTheWarehouseDetailsPage() {
        this.warehouseDetailsPage.get();
    }

    @When("I click on the update warehouse button")
    public void iClickOnTheUpdateWarehouseButton() {
        this.warehouseDetailsPage.clickUpdateWarehouse();
    }

    @Then("I should see the update warehouse form modal")
    public void iShouldSeeTheUpdateWarehouseFormModal() {
        assertTrue(this.warehouseDetailsPage.updateWarehouseModalDisplayed());
    }
}
