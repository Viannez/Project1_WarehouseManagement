package com.skillstorm.cucumber.definitions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.skillstorm.selenium.ProductPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteProductSteps {
    private WebDriver driver;
    private ProductPage productPage;

    // Setup cucumber ChromeOptions and instantiate a ChromeDriver and ProductPage
    @Before("@delete-product")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        options.addArguments("--headless", "--disable-dev-shm-usage", "--ignore-ssl-errors=yes", "'--ignore-certificate-errors'");

        this.driver = new ChromeDriver(options);
        
        this.productPage = new ProductPage(driver);
    }
    // Tear down
    @After("@delete-product")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }

    // Navigates to /product page
    // and checks if there are existing products
    @Given("I am on the products page that has product cards")
    public void iAmOnTheProductsPageThatHasProductCards() {
        this.productPage.get();
        assertTrue(this.productPage.productCardsAreDisplayed());
    }

    // Checks if the product with matching name exists
    @And("There exists a product card named {string}")
    public void thereExistsProductCards(String name) {
        assertTrue(this.productPage.productCardIsDisplayed(name));
    }

    // Simulates a button click to delete the product on the /product page
    @When("I click the delete button on the product card named {string}")
    public void iClickOnTheDeleteProductButton(String name) {
        this.productPage.clickDeleteProductCard(name);
    }

    // Checks if the deleted product no longer exists on the /product page
    @Then("I should no longer see a card named {string} on product page")
    public void iShouldNotSeeTheProductCard(String name) {
        assertFalse(this.productPage.productCardIsDisplayed(name));
    }
}
