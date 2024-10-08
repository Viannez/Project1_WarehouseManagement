package com.skillstorm.selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class WarehousePage {
    private final WebDriver driver;
    private static final String url = "http://mystery-box-warehouses-frontend.s3-website-us-east-1.amazonaws.com/warehouse";
    // private static final String url = "http://localhost:5173/warehouse";
    //navbar
    @FindBy(id="nav-product")
    private WebElement goToProductsPage;

    @FindBy(id="nav-warehouse")
    private WebElement goToWarehousesPage;
    
    //buttons
    @FindBy(id="add-warehouse")
    private WebElement addWarehouseButton;

    @FindBy(id="sort-warehouse")
    private WebElement sortWarehouse;

    @FindBy(id="add-warehouse-modal")
    private WebElement addWarehouseModal;

    //modal form 
    @FindBy(css = "input[id='warehouse-name']")
    private WebElement nameField;

    @FindBy(css = "input[id='warehouse-address']")
    private WebElement addressField;

    @FindBy(css = "input[id='warehouse-capacity']")
    private WebElement capacityField;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    //warehouse cards
    @FindBy(className = "usa-card__container")
    private List<WebElement> cards;

     /**
     * Initializes the driver and sets an implicit wait 
     */
    public WarehousePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * navigating to the warehouse page
     * pause execution for 1000 mili sec before navigating
     */
    public void get() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.driver.get(url);
    }

    /**
     * clicking navbar option
     * pause execution for 1000 mili sec before navigating
     */
    public void selectNavBar(String page) {
        if(page.equals("Products")){
            
            goToProductsPage.click();
        }
        else if(page.equals("Warehouses")){
            System.out.println("clicked");
            goToWarehousesPage.click();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

       /**
     * select sort by warehouse
     * pause execution for 1000 mili sec before navigating
     */
    public void selectSortBy(String selectOption) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Select select = new Select(sortWarehouse);
        select.selectByVisibleText(selectOption);
    }

    /**
     * check if products are sorted
     */
    public boolean isOrdered(String sortOption) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(cards.size() == 1)
        {
            return true;
        }
        // sort by id
        boolean found = true;
        int prev = 0;
        if(sortOption.equals("By ID"))
        {
            for(WebElement list:cards){
                WebElement wID = list.findElement(By.id("warehouse-id"));
                int current = Integer.parseInt(wID.getText().substring(wID.getText().lastIndexOf(":") + 2));
                if(current <= prev){
                    return false;
                }
                else{
                    prev = current;
                }
            }
        }
        // sort by capacity
        else if(sortOption.equals("By Capacity"))
        {
            for(WebElement list:cards){
                WebElement wCapacity = list.findElement(By.id("warehouse-capacity"));
                int current = Integer.parseInt(wCapacity.getText().substring(wCapacity.getText().lastIndexOf(":") + 1));
                if(current < prev){
                    return false;
                }
                else{
                    prev = current;
                }
            }
        }
        
        return found;
    }

    /**
     * navigating to the warehouse page
     * pause execution for 1000 mili sec before navigating
     */
    public void clickAddWarehouse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addWarehouseButton.click();
    }

    /**
     * warehouse modal is displayed
     */
    public boolean addWarehouseModalDisplayed() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return addWarehouseModal.isDisplayed(); 
    }

    /**
     * entering the name into the name field
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
     * entering the address into the address field
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
     * entering the capacity into the capacity field
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
        submitButton.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * checks if there are any warehouse cards
     */
    public boolean warehouseCardsAreDisplayed()
    {
        System.out.println(cards.size());
        if(!cards.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * check if warehouse card matches the exact values
     */
    public boolean warehouseCardIsDisplayed(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean found = false;
        for(WebElement list:cards){
            WebElement wName = list.findElement(By.id("warehouse-name"));

            if(wName.getText().equals(name)){
                found = true;
            }
        }
        return found;
    }

    /**
     * check if warehouse card matches the exact values
     */
    public boolean warehouseCardIsDisplayed(String name, String address, String capacity) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean found = false;
        for(WebElement list:cards){
            WebElement wName = list.findElement(By.id("warehouse-name"));
            WebElement wAddress = list.findElement(By.id("warehouse-address"));
            WebElement wCapacity = list.findElement(By.id("warehouse-capacity"));
            
            System.out.print(wName.getText()+ " ");
            System.out.print(wAddress.getText()+ " ");
            System.out.println(wCapacity.getText().substring(wCapacity.getText().lastIndexOf("/") + 1));

            if(wName.getText().equals(name) && 
                wAddress.getText().equals(address) &&
                wCapacity.getText().substring(wCapacity.getText().lastIndexOf("/") + 1).equals(capacity)){
                found = true;
            }
        }
        
        return found; 
    }

    /**
     * click delete button of warehouse card with name
     */
    public void clickDeleteWarehouseCard(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement deleteButton;
        for(WebElement list:cards){
            WebElement wName = list.findElement(By.id("warehouse-name"));
            if(wName.getText().equals(name)){
                deleteButton=list.findElement(By.id("delete-warehouse"));
                Actions actions = new Actions(driver);
                actions.moveToElement(deleteButton).click().perform();
                // deleteButton.click();
                break;
            }
        }
    }

}
