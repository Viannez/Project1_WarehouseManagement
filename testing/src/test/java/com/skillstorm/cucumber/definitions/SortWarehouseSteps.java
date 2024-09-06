package com.skillstorm.cucumber.definitions;

import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.skillstorm.selenium.WarehousePage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SortWarehouseSteps {
    private WebDriver driver;
    private WarehousePage warehousePage;
    

    // Setup cucumber ChromeOptions and instantiate a ChromeDriver and WarehousePage
    @Before("@sort-warehouse")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        options.addArguments("--headless", "--disable-dev-shm-usage", "--ignore-ssl-errors=yes", "'--ignore-certificate-errors'");

        this.driver = new ChromeDriver(options);
        
        this.warehousePage = new WarehousePage(driver);
    }
    // Tear down
    @After("@sort-warehouse")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }

    // Navigates to /warehouse page
    // and checks if there are existing warehouses
    @Given("I am on the warehouses page with unsorted warehouses")
    public void iAmOnTheWarehousesPageThatHasWarehouseCards() {
        this.warehousePage.get();
        assertTrue(this.warehousePage.warehouseCardsAreDisplayed());
    }

    // Simulates a selecting sort by clicking an option on the /warehouse page
    @When("I select the sort warehouse option with text {string}")
    public void iSelectSortOption(String selectOption) {
        this.warehousePage.selectSortBy(selectOption);
    }

    // Checks if the deleted warehouse no longer exists on the /warehouse page
    @Then("The warehouse list on warehouse page should be ordered by {string}")
    public void iShouldSeeOrderedWarehouseList(String selectOption) {
        assertTrue(this.warehousePage.isOrdered(selectOption));
    }
}
