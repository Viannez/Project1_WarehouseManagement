package com.skillstorm.cucumber.defintions;

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

    @Before("@delete-warehouse")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        this.driver = new ChromeDriver(options);
        this.warehousePage = new WarehousePage(driver);
    }
    @After("@delete-warehouse")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }

    //Get to modal
    @Given("I am on the warehouses page that has warehouse cards")
    public void iAmOnTheWarehousesPageThatHasWarehouseCards() {
        this.warehousePage.get();
        assertTrue(this.warehousePage.warehouseCardsAreDisplayed());
    }

    @And("There exists a warehouse card named {string}")
    public void thereExistsWarehouseCards(String name) {
        assertTrue(this.warehousePage.warehouseCardIsDisplayed(name));
    }

    @When("I click the delete button on the warehouse card named {string}")
    public void iClickOnTheAddWarehouseButton(String name) {
        this.warehousePage.clickDeleteWarehouseCard(name);
    }

    @Then("I should no longer see a card named {string} on warehouse page")
    public void iShouldSeeTheAddWarehouseFormModal(String name) {
        assertFalse(this.warehousePage.warehouseCardIsDisplayed(name));
    }
}
