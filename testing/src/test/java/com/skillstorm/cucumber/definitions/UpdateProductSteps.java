package com.skillstorm.cucumber.definitions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.skillstorm.selenium.ProductDetailsPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateProductSteps {
    private WebDriver driver;
    private ProductDetailsPage productDetailsPage;
    private String productDetailsUrl;

    // Setup cucumber ChromeOptions and instantiate a ChromeDriver and ProductDetailsPage
    @Before("@update-product")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        options.addArguments("--headless", "--disable-dev-shm-usage", "--ignore-ssl-errors=yes", "'--ignore-certificate-errors'");

        this.driver = new ChromeDriver(options);
        
        this.productDetailsPage = new ProductDetailsPage(driver);
    }
    // Tear down
    @After("@update-product")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }
    
    // Navigate to the product with matching name parameter
    @Given("I am on the product {string} details page")
    public void iAmOnTheProductDetailsPage(String productName) {
        this.productDetailsUrl = this.productDetailsPage.get(productName);
        this.productDetailsPage.getUrl(productDetailsUrl);
    }

    // Call method to simulate a click on the button to update a product
    @When("I click on the update product button")
    public void iClickOnTheUpdateProductButton() {
        this.productDetailsPage.clickUpdateProduct();
    }

    // Checks if the update product form is displayed
    @Then("I should see the update product form modal")
    public void iShouldSeeTheUpdateProductFormModal() {
        assertTrue(this.productDetailsPage.updateProductModalDisplayed());
    }

    // Navigates to the product with matching name, simulates a button click to update that product
    // and checks if the update product form is displayed
    @Given("I have the update product {string} form modal open")
    public void iAmOnTheUpdateProductFormModal(String productName) {
        this.productDetailsUrl = this.productDetailsPage.get(productName);
        this.productDetailsPage.getUrl(productDetailsUrl);
        this.productDetailsPage.clickUpdateProduct();
        assertTrue(this.productDetailsPage.updateProductModalDisplayed());
    }

    // Checks if the update product form is displayed
    // and sets the product's updated name, category, and price
    @When("I enter valid update fields with name = {string}, category = {string}, price = {string}")
    public void iEnterValidFormInputs(String updatedName, String updatedCategory, String updatedPrice) {
        if(this.productDetailsPage.updateProductModalDisplayed()){
            // System.out.println("Update product modal is displaying");
        }
        System.out.println(this.driver.getCurrentUrl());
        this.driver.switchTo().activeElement();
        this.productDetailsPage.setName(updatedName);
        this.productDetailsPage.setCategory(updatedCategory);
        this.productDetailsPage.setPrice(updatedPrice);
    }

    // Simulates a button click to submit the update product form
    @And("I click the update product form submit button")
    public void iClickTheSubmitButton() {
        this.productDetailsPage.clickSubmitButton();
    }

    // Checks if the product was updated according to the update product form
    @Then("I should see the product updated with name = {string}, category = {string}, price = {string}")
    public void iShouldSeeACardWithMatchingInputs(String updatedName, String updatedCategory, String updatedPrice) {
        this.driver.navigate().refresh();
        assertTrue(this.productDetailsPage.updatedProductIsDisplayed(updatedName, updatedCategory, updatedPrice));
    }
}
