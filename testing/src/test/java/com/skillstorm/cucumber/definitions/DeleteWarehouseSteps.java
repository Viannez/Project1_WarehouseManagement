package com.skillstorm.cucumber.definitions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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

public class DeleteWarehouseSteps {
    private WebDriver driver;
    private WarehousePage warehousePage;

    // Setup cucumber ChromeOptions and instantiate a ChromeDriver and WarehousePage
    @Before("@delete-warehouse")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        options.addArguments("--headless", "--disable-dev-shm-usage", "--ignore-ssl-errors=yes", "'--ignore-certificate-errors'");

        this.driver = new ChromeDriver(options);
        
        this.warehousePage = new WarehousePage(driver);
    }
    // Tear down
    @After("@delete-warehouse")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }

    // Navigates to /warehouse page
    // and checks if there are existing warehouses
    @Given("I am on the warehouses page that has warehouse cards")
    public void iAmOnTheWarehousesPageThatHasWarehouseCards() {
        this.warehousePage.get();
        assertTrue(this.warehousePage.warehouseCardsAreDisplayed());
    }

    // Checks if the warehouse with matching name exists
    @And("There exists a warehouse card named {string}")
    public void thereExistsWarehouseCards(String name) {
        assertTrue(this.warehousePage.warehouseCardIsDisplayed(name));
    }

    // Simulates a button click to delete the warehouse on the /warehouse page
    @When("I click the delete button on the warehouse card named {string}")
    public void iClickOnTheDeleteWarehouseButton(String name) {
        this.warehousePage.clickDeleteWarehouseCard(name);
    }

    // Checks if the deleted warehouse no longer exists on the /warehouse page
    @Then("I should no longer see a card named {string} on warehouse page")
    public void iShouldNotSeeTheAddWarehouseCard(String name) {
        assertFalse(this.warehousePage.warehouseCardIsDisplayed(name));
    }
}
