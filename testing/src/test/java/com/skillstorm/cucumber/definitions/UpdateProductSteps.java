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

    @Before("@update-product")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        options.addArguments("--headless", "--disable-dev-shm-usage", "--ignore-ssl-errors=yes", "'--ignore-certificate-errors'");

        this.driver = new ChromeDriver(options);
        
        this.productDetailsPage = new ProductDetailsPage(driver);
    }
    @After("@update-product")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }
    
    //Get to modal
    @Given("I am on the product {string} details page")
    public void iAmOnTheProductDetailsPage(String productName) {
        this.productDetailsPage.get(productName);
    }

    @When("I click on the update product button")
    public void iClickOnTheUpdateProductButton() {
        this.productDetailsPage.clickUpdateProduct();
    }

    @Then("I should see the update product form modal")
    public void iShouldSeeTheUpdateProductFormModal() {
        assertTrue(this.productDetailsPage.updateProductModalDisplayed());
    }

    //Update prodyuct valid
    @Given("I have the update product {string} form modal open")
    public void iAmOnTheUpdateProductFormModal(String productName) {
        this.productDetailsPage.get(productName);
        this.productDetailsPage.clickUpdateProduct();
        assertTrue(this.productDetailsPage.updateProductModalDisplayed());
    }

    @When("I enter valid update fields with name = {string}, category = {string}, price = {string}")
    public void iEnterValidFormInputs(String updatedName, String updatedCategory, String updatedPrice) {
        if(this.productDetailsPage.updateProductModalDisplayed()){
            System.out.println("Update product modal is displaying");

        }
        this.driver.switchTo().activeElement();
        this.productDetailsPage.setName(updatedName);
        this.productDetailsPage.setCategory(updatedCategory);
        this.productDetailsPage.setPrice(updatedPrice);
    }

    @And("I click the update product form submit button")
    public void iClickTheSubmitButton() {
        this.productDetailsPage.clickSubmitButton();
    }

    @Then("I should see the product updated with name = {string}, category = {string}, price = {string}")
    public void iShouldSeeACardWithMatchingInputs(String updatedName, String updatedCategory, String updatedPrice) {
        this.driver.navigate().refresh();
        assertTrue(this.productDetailsPage.updatedProductIsDisplayed(updatedName, updatedCategory, updatedPrice));
    }
}
