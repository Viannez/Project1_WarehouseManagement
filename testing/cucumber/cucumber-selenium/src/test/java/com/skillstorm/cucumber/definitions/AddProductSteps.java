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

    @Before("@add-product")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        this.driver = new ChromeDriver(options);
        this.productPage = new ProductPage(driver);
    }
    @After("@add-product")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }

    //Get to modal
    @Given("I am on the products page")
    public void iAmOnTheProductsPage() {
        this.productPage.get();
    }

    @When("I click on the add product button")
    public void iClickOnTheAddProductButton() {
        this.productPage.clickAddProduct();
    }

    @Then("I should see the add product form modal")
    public void iShouldSeeTheAddProductFormModal() {
        assertTrue(this.productPage.addProductModalDisplayed());
    }

    //Create and add warehouse
    @Given("I have product modal open")
    public void iAmOnTheAddProducyModal() {
        this.productPage.get();
        this.productPage.clickAddProduct();
        assertTrue(this.productPage.addProductModalDisplayed());
    }

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

    @And("I click the submit button")
    public void iClickTheSubmitButton() {
        this.productPage.clickSubmitButton();
    }

    @Then("I should see a card with matching {string} and {string} and {string}")
    public void iShouldSeeACardWithMatchingInputs(String name, String price, String category) {
        this.driver.navigate().refresh();
        assertTrue(this.productPage.productCardIsDisplayed(name, price, category));
    }

}
