package com.skillstorm.selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {
    private final WebDriver driver;
    private static final String url = "http://mystery-box-warehouses-frontend.s3-website-us-east-1.amazonaws.com/product";
    // private static final String url = "http://localhost:5173/product";
    @FindBy(id="add-product")
    private WebElement addProductButton;

    @FindBy(id="sort-product")
    private WebElement sortProduct;

    @FindBy(id="add-product-modal")
    private WebElement addProductModal;

    //modal form 
    @FindBy(css = "input[id='product-name']")
    private WebElement nameField;

    @FindBy(css = "select[id='product-category']")
    private WebElement categorySelect;

    @FindBy(css = "input[id='product-price']")
    private WebElement priceField;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    //product cards
    @FindBy(className = "usa-card__container")
    private List<WebElement> cards;

     /**
     * Initializes the driver and sets an implicit wait 
     */
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * navigating to the product page
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
     * navigating to the product page
     * pause execution for 1000 mili sec before navigating
     */
    public void clickAddProduct() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addProductButton.click();
    }

    /**
     * product modal is displayed
     */
    public boolean addProductModalDisplayed() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return addProductModal.isDisplayed(); 
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
     * selecting the category id 
     */
    public void setCategory(String category) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Select select = new Select(categorySelect);
        select.selectByVisibleText(category);
    }

    /**
     * entering the price into the price field
     */
    public void setPrice(String price) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        priceField.sendKeys(price);
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
     * checks if there are any product cards
     */
    public boolean productCardsAreDisplayed()
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
     * check if product card matches the exact values
     */
    public boolean productCardIsDisplayed(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean found = false;
        for(WebElement list:cards){
            WebElement pName = list.findElement(By.id("product-name"));

            if(pName.getText().equals(name)){
                found = true;
            }
        }
        return found;
    }

    /**
     * check if product card matches the exact values
     */
    public boolean productCardIsDisplayed(String name, String price, String category) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean found = false;
        for(WebElement list:cards){
            WebElement pName = list.findElement(By.id("product-name"));
            WebElement pCategory = list.findElement(By.id("product-category"));
            WebElement pPrice = list.findElement(By.id("product-price"));
            
            System.out.print(pName.getText()+ " ");
            System.out.print(pCategory.getText().substring(6)+ " ");
            System.out.println(pPrice.getText().substring(1));

            if(pName.getText().equals(name) && 
                pCategory.getText().substring(6).equals(category) &&
                pPrice.getText().substring(1).equals(price)){
                found = true;
            }
        }
        
        return found; 
    }

    /**
     * click delete button of product card with name
     */
    public void clickDeleteProductCard(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement deleteButton;
        for(WebElement list:cards){
            WebElement pName = list.findElement(By.id("product-name"));

            if(pName.getText().equals(name)){
                deleteButton=list.findElement(By.id("delete-product"));
                deleteButton.click();
                break;
            }
        }
    }

}
