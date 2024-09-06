package com.skillstorm.cucumber.definitions;

import static org.junit.jupiter.api.Assertions.assertTrue;
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

public class AddProductSteps {
    private WebDriver driver;
    private ProductPage productPage;

    // Setup cucumber ChromeOptions and instantiate a ChromeDriver and ProductPage
    @Before("@add-product")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        options.addArguments("--headless", "--disable-dev-shm-usage", "--ignore-ssl-errors=yes", "'--ignore-certificate-errors'");

        this.driver = new ChromeDriver(options);
        
        this.productPage = new ProductPage(driver);
    }
    // Tear down
    @After("@add-product")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }

    // Navigates to the /product page
    @Given("I am on the products page")
    public void iAmOnTheProductsPage() {
        this.productPage.get();
    }

    // Simulates a button click to add a product
    @When("I click on the add product button")
    public void iClickOnTheAddProductButton() {
        this.productPage.clickAddProduct();
    }

    // Checks if the add product form modal is displayed
    @Then("I should see the add product form modal")
    public void iShouldSeeTheAddProductFormModal() {
        assertTrue(this.productPage.addProductModalDisplayed());
    }

    // Navigates to /product page, simulates a button click to add a product
    // and checks if the add product form is displayed
    @Given("I have product modal open")
    public void iAmOnTheAddProducyModal() {
        this.productPage.get();
        this.productPage.clickAddProduct();
        assertTrue(this.productPage.addProductModalDisplayed());
    }

    // Checks if the add product form is displayed
    // and sets the product's name, category, and price based on arguments
    @When("I enter valid {string} and {string} and select {string}")
    public void iEnterValidFormInputs(String name, String price, String category) {
        if(this.productPage.addProductModalDisplayed()){
            System.out.println("Modal is displaying");

        }
        this.driver.switchTo().activeElement();
        this.productPage.setName(name);
        this.productPage.setCategory(category);
        this.productPage.setPrice(price);
    }

    // Simulates clicking the submit button on the add product form
    @And("I click the submit button")
    public void iClickTheSubmitButton() {
        this.productPage.clickSubmitButton();
    }

    // Checks if the added product is displayed on the /product page
    @Then("I should see a card with matching {string} and {string} and {string}")
    public void iShouldSeeACardWithMatchingInputs(String name, String price, String category) {
        this.driver.navigate().refresh();
        assertTrue(this.productPage.productCardIsDisplayed(name, price, category));
    }

}
