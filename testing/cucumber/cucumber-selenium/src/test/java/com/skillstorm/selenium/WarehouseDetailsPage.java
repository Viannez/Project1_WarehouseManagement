package com.skillstorm.selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WarehouseDetailsPage {
    private final WebDriver driver;
    // private static final String url = "http://mystery-box-warehouses-frontend.s3-website-us-east-1.amazonaws.com/warehouse";
    private static final String url = "http://localhost:5173/warehouse";
    private static String warehouseDetailUrl = ""; // to be updated based on first existing warehouse on /warehouse page

    //warehouse cards
    @FindBy(className = "usa-card__container")
    private List<WebElement> cards;

    //below web elements on a warehouse's details page
    @FindBy(id="update-warehouse")
    private WebElement updateWarehouseButton;
    
    @FindBy(id="warehouse-add-product")
    private WebElement addProductToWarehouseButton;

    @FindBy(id="update-warehouse-modal")
    private WebElement updateWarehouseModal;

    @FindBy(id="warehouse-add-product-modal")
    private WebElement addProductToWarehouseModal;

    @FindBy(id="current-warehouse-name")
    private WebElement currentWarehouseName;

    @FindBy(id="current-warehouse-address")
    private WebElement currentWarehouseAddress;

    @FindBy(id="current-warehouse-capacity")
    private WebElement currentWarehouseCapacity;

    //update warehouse modal form web elements
    @FindBy(css = "input[id='warehouse-name']")
    private WebElement nameField;

    @FindBy(css = "input[id='warehouse-address']")
    private WebElement addressField;

    @FindBy(css = "input[id='warehouse-capacity']")
    private WebElement capacityField;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    //add product to warehouse modal form web elements
    @FindBy(css = "select[id='product-name']")
    private WebElement productSelect;

    @FindBy(css = "input[id='product-stock']")
    private WebElement stockField;

    @FindBy(css = "button[type='submit']")
    private WebElement productSubmitButton;

    //warehouse's product cards
    @FindBy(id = "warehouseproduct-card")
    private List<WebElement> warehouseProductCards;

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
    * clicking the add product to warehouse button
    * pause execution for 1000 mili sec before clicking
    */
    public void clickAddProductToWarehouse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addProductToWarehouseButton.click();
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
    * update warehouse modal is displayed
    */
    public boolean addProductToWarehouseModalDisplayed() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return addProductToWarehouseModal.isDisplayed(); 
    }

    /**
    * checks if a specific product exists based on the name
    */
    public boolean isAProductStockedInTheWarehouse(String productName) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean found = false;
        for(WebElement list:warehouseProductCards){
            WebElement wName = list.findElement(By.id("warehouseproduct-name"));

            if(wName.getText().equals(productName)){
                found = true;
            }
        }
        return found;
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
    * setting the product name into the add product to warehouse modal
    */
    public void setProductName(String productName) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productSelect.sendKeys(productName);
    }

    /**
    * setting the product stock into the add product to warehouse modal
    */
    public void setProductStock(String productStock) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stockField.sendKeys(productStock);
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

    /**
    * click on product submit button on add product to warehouse form modal
    * pause execution for 1000 mili sec before navigating
    */
    public void clickProductSubmitButton() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        productSubmitButton.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
    * click on product delete button for a particular warehouse
    * pause execution for 1000 mili sec before navigating
    */
    public void clickDeleteProductFromWarehouseButton(String productName) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement deleteButton;
        for(WebElement list:warehouseProductCards){
            WebElement wName = list.findElement(By.id("warehouseproduct-name"));
            if(wName.getText().equals(productName)){
                deleteButton=list.findElement(By.id("delete-warehouseproduct-button"));
                Actions actions = new Actions(driver);
                actions.moveToElement(deleteButton).click().perform();
                break;
            }
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

        System.out.println("Updated warehouse capacity is " + updatedCapacity);
        System.out.println("Current warehouse capacity is " + currentWarehouseCapacity.getText().substring(currentWarehouseCapacity.getText().lastIndexOf("/") + 1));
        if(!updatedName.equals(currentWarehouseName.getText())){
            System.out.println("Warehouse name was not updated.");
            return false;
        }
        if(!updatedAddress.equals(currentWarehouseAddress.getText())){
            System.out.println("Warehouse address was not updated.");
            return false;
        }
        if(!updatedCapacity.equals(currentWarehouseCapacity.getText().substring(currentWarehouseCapacity.getText().lastIndexOf("/") + 1))){
            System.out.println("Warehouse capacity was not updated.");
            return false;
        }
        System.out.println("Warehouse: " + currentWarehouseName.getText() + " successfully updated.");
        return true;
    }

    // checks if the added product is found in the warehouse
    public boolean addedProductIsDisplayed(String productName, String productStock){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.driver.navigate().to(warehouseDetailUrl);
        
        boolean found = false;
        for(WebElement list:warehouseProductCards){
            WebElement whProductName = list.findElement(By.id("warehouseproduct-name"));
            WebElement whProductStock = list.findElement(By.id("warehouseproduct-stock"));
            
            System.out.println(whProductName.getText());
            System.out.println(whProductStock.getText().substring(whProductStock.getText().lastIndexOf(":") + 2));

            if(whProductName.getText().equals(productName) && 
                whProductStock.getText().substring(whProductStock.getText().lastIndexOf(":") + 2).equals(productStock)){
                found = true;
            }
        }
        
        return found; 
    }

    // checks if the deleted product is found in the warehouse
    public boolean isProductDeletedFromWarehouse(String productName){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.driver.navigate().to(warehouseDetailUrl);
        
        boolean isProductExisting = false;
        for(WebElement list:warehouseProductCards){
            WebElement whProductName = list.findElement(By.id("warehouseproduct-name"));
            System.out.println(whProductName.getText());

            if(whProductName.getText().equals(productName)){
                isProductExisting = true; // the deleted product exists, something went wrong
            }
        }
        
        return isProductExisting; 
    }
}
