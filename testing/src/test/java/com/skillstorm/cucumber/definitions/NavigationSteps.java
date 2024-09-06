package com.skillstorm.cucumber.definitions;

import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.skillstorm.selenium.ProductPage;
import com.skillstorm.selenium.WarehousePage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NavigationSteps {
    private WebDriver driver;
    private ProductPage productPage;
    private WarehousePage warehousePage;
    

    // Setup cucumber ChromeOptions and instantiate a ChromeDriver and ProductPage
    @Before("@navigate")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        options.addArguments("--headless", "--disable-dev-shm-usage", "--ignore-ssl-errors=yes", "'--ignore-certificate-errors'");

        this.driver = new ChromeDriver(options);
        
        this.productPage = new ProductPage(driver);
        this.warehousePage = new WarehousePage(driver);
    }
    // Tear down
    @After("@navigate")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }

    // Navigates to /firstPage
    @Given("I am currently on the {string}")
    public void iAmOnTheProductsPageThatHasProductCards(String firstPage) {
        if(firstPage.equals("Products"))
        {
            this.productPage.get();
        }
        else if(firstPage.equals("Warehouses"))
        {
            this.warehousePage.get();
        }
    }

    // Simulates a selecting sort by clicking an option on the /product page
    @When("I click on the correct button for the {string}")
    public void iSelectSortOption(String secondPage) {
        if(this.driver.getCurrentUrl().contains("product"))
        {
            this.productPage.selectNavBar(secondPage);
        }
        else if(this.driver.getCurrentUrl().contains("warehouse"))
        {
            this.warehousePage.selectNavBar(secondPage);
        }
    }

    // Checks if the deleted product no longer exists on the /product page
    @Then("I be on the {string} page")
    public void iShouldSeeOrderedProductList(String secondPage) {
        // System.out.println(this.driver.getCurrentUrl());
        if(this.driver.getCurrentUrl().contains("product") && secondPage.contains("P"))
        {
            assertTrue(true);
        }
        else if(this.driver.getCurrentUrl().contains("warehouse") && secondPage.contains("W"))
        {
            assertTrue(true);
        }
        else{
            assertTrue(false);
        }
        
    }
}
