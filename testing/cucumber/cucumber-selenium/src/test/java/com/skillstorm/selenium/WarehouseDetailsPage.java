package com.skillstorm.selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WarehouseDetailsPage {
    private final WebDriver driver;
    private static final String url = "http://mystery-box-warehouses-frontend.s3-website-us-east-1.amazonaws.com/warehouse";
    // private static final String url = "http://localhost:5173/warehouse";
    private static String warehouseDetailUrl = ""; // to be updated based on first existing warehouse on /warehouse page

    //warehouse cards
    @FindBy(className = "usa-card__container")
    private List<WebElement> cards;

    //below web elements on a warehouse's details page
    @FindBy(id="update-warehouse")
    private WebElement updateWarehouseButton;

    @FindBy(id="update-warehouse-modal")
    private WebElement updateWarehouseModal;

    @FindBy(id="current-warehouse-name")
    private WebElement currentWarehouseName;

    @FindBy(id="current-warehouse-address")
    private WebElement currentWarehouseAddress;

    @FindBy(id="current-warehouse-capacity")
    private WebElement currentWarehouseCapacity;

    //modal form web elements
    @FindBy(css = "input[id='warehouse-name']")
    private WebElement nameField;

    @FindBy(css = "input[id='warehouse-address']")
    private WebElement addressField;

    @FindBy(css = "input[id='warehouse-capacity']")
    private WebElement capacityField;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    /**
    * Initializes the driver and sets an implicit wait 
    */
    public WarehouseDetailsPage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * navigating to the warehouse details page from the first existing warehouse
     * pause execution for 1000 mili sec before navigating
     */
    public void get() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // navigate to the list of warehouses page
        this.driver.get(url);

        // if there is atleast one existing warehouse
        if(!cards.isEmpty()){
            // update the url to navigate to the first existing warehouse
            for(WebElement list:cards){
                WebElement existingWarehouseId = list.findElement(By.id("warehouse-id"));
                String idString = existingWarehouseId.getText();
                warehouseDetailUrl = url + "/" + idString.substring(idString.lastIndexOf(":") + 2); // note - "ID: 2" would append '2' to the url
                break;
            }
        }
        else{
            System.out.println("No warehouses exist!");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // navigate to the first existing warehouse's details page
        if(!warehouseDetailUrl.isEmpty()){
            this.driver.get(warehouseDetailUrl);
        }
    }

    /**
    * clicking the update warehouse button
    * pause execution for 1000 mili sec before clicking
    */
    public void clickUpdateWarehouse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateWarehouseButton.click();
    }

    /**
    * update warehouse modal is displayed
    */
    public boolean updateWarehouseModalDisplayed() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return updateWarehouseModal.isDisplayed(); 
    }

    /**
    * entering the name into the update name field
    */
    public void setName(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nameField.sendKeys(name);
    }

    /**
    * entering the address into the update address field
    */
    public void setAddress(String address) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addressField.sendKeys(address);
    }


    /**
    * entering the capacity into the update capacity field
    */
    public void setCapacity(String capacity) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        capacityField.sendKeys(capacity);
    }

    /**
    * click on submit button on modal
    * pause execution for 1000 mili sec before navigating
    */
    public void clickSubmitButton() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        submitButton.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean updatedWarehouseIsDisplayed(String updatedName, String updatedAddress, String updatedCapacity){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.driver.navigate().to(warehouseDetailUrl);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Updated warehouse name is " + updatedName);
        System.out.println("Current warehouse name is " + currentWarehouseName.getText());
        System.out.println("Updated warehouse address is " + updatedAddress);
        System.out.println("Current warehouse address is " + currentWarehouseAddress.getText());

        updatedCapacity = "0/" + updatedCapacity;
        System.out.println("Updated warehouse capacity is " + updatedCapacity);
        System.out.println("Current warehouse capacity is " + currentWarehouseCapacity.getText());
        if(!updatedName.equals(currentWarehouseName.getText())){
            System.out.println("Warehouse name was not updated.");
            return false;
        }
        if(!updatedAddress.equals(currentWarehouseAddress.getText())){
            System.out.println("Warehouse address was not updated.");
            return false;
        }
        if(!updatedCapacity.equals(currentWarehouseCapacity.getText())){
            System.out.println("Warehouse capacity was not updated.");
            return false;
        }
        System.out.println("Warehouse: " + currentWarehouseName.getText() + " successfully updated.");
        return true;
    }
}
