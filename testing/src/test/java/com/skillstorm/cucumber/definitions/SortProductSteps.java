package com.skillstorm.cucumber.definitions;

import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.skillstorm.selenium.ProductPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SortProductSteps {
    private WebDriver driver;
    private ProductPage productPage;
    

    // Setup cucumber ChromeOptions and instantiate a ChromeDriver and ProductPage
    @Before("@sort-product")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        options.addArguments("--headless", "--disable-dev-shm-usage", "--ignore-ssl-errors=yes", "'--ignore-certificate-errors'");

        this.driver = new ChromeDriver(options);
        
        this.productPage = new ProductPage(driver);
    }
    // Tear down
    @After("@sort-product")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }

    // Navigates to /product page
    // and checks if there are existing products
    @Given("I am on the products page with unsorted products")
    public void iAmOnTheProductsPageThatHasProductCards() {
        this.productPage.get();
        assertTrue(this.productPage.productCardsAreDisplayed());
    }

    // Simulates a selecting sort by clicking an option on the /product page
    @When("I select the option with text {string}")
    public void iSelectSortOption(String selectOption) {
        this.productPage.selectSortBy(selectOption);
    }

    // Checks if the deleted product no longer exists on the /product page
    @Then("The product list on product page should be ordered by {string}")
    public void iShouldSeeOrderedProductList(String selectOption) {
        assertTrue(this.productPage.isOrdered(selectOption));
    }
}
