package com.skillstorm.cucumber.defintions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.skillstorm.selenium.WarehouseDetailsPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateWarehouseSteps {
    private WebDriver driver;
    private WarehouseDetailsPage warehouseDetailsPage;

    @Before("@update-warehouse")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("127");
        this.driver = new ChromeDriver(options);
        this.warehouseDetailsPage = new WarehouseDetailsPage(driver);
    }
    @After("@update-warehouse")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }

    //Get to modal
    @Given("I am on the warehouse details page")
    public void iAmOnTheWarehouseDetailsPage() {
        this.warehouseDetailsPage.get();
    }

    @When("I click on the update warehouse button")
    public void iClickOnTheUpdateWarehouseButton() {
        this.warehouseDetailsPage.clickUpdateWarehouse();
    }

    @Then("I should see the update warehouse form modal")
    public void iShouldSeeTheUpdateWarehouseFormModal() {
        assertTrue(this.warehouseDetailsPage.updateWarehouseModalDisplayed());
    }

    @Given("I have the update warehouse form modal open")
    public void iAmOnTheUpdateWarehouseFormModal() {
        this.warehouseDetailsPage.get();
        this.warehouseDetailsPage.clickUpdateWarehouse();
        assertTrue(this.warehouseDetailsPage.updateWarehouseModalDisplayed());
    }

    @When("I enter valid update fields with name = {string}, address = {string}, capacity = {string}")
    public void iEnterValidFormInputs(String updatedName, String updatedAddress, String updatedCapacity) {
        if(this.warehouseDetailsPage.updateWarehouseModalDisplayed()){
            System.out.println("Update warehouse modal is displaying");

        }
        this.driver.switchTo().activeElement();
        this.warehouseDetailsPage.setName(updatedName);
        this.warehouseDetailsPage.setAddress(updatedAddress);
        this.warehouseDetailsPage.setCapacity(updatedCapacity);
    }

    @And("I click the update warehouse form submit button")
    public void iClickTheSubmitButton() {
        this.warehouseDetailsPage.clickSubmitButton();
    }

    @Then("I should see the warehouse updated with name = {string}, address = {string}, capacity = {string}")
    public void iShouldSeeACardWithMatchingInputs(String updatedName, String updatedAddress, String updatedCapacity) {
        this.driver.navigate().refresh();
        assertTrue(this.warehouseDetailsPage.updatedWarehouseIsDisplayed(updatedName, updatedAddress, updatedCapacity));
    }
}
