package com.skillstorm.cucumber.defintions;

// import static org.junit.Assert.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

    @Before("@delete-product")
    public void before() {
        this.driver = new ChromeDriver();
        this.productPage = new ProductPage(driver);
    }
    @After("@delete-product")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }

    //Get to modal
    @Given("I am on the products page that has product cards")
    public void iAmOnTheProductsPageThatHasProductCards() {
        this.productPage.get();
        assertTrue(this.productPage.productCardsAreDisplayed());
    }

    @And("There exists a product card named {string}")
    public void thereExistsProductCards(String name) {
        assertTrue(this.productPage.productCardIsDisplayed(name));
    }

    @When("I click the delete button on the product card named {string}")
    public void iClickOnTheAddProductButton(String name) {
        this.productPage.clickDeleteProductCard(name);
    }

    @Then("I should no longer see a card named {string} on product page")
    public void iShouldSeeTheAddProductFormModal(String name) {
        assertFalse(this.productPage.productCardIsDisplayed(name));
    }
}
