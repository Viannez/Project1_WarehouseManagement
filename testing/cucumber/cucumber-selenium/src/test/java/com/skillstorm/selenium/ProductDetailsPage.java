package com.skillstorm.selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {
    private final WebDriver driver;
    // private static final String url = "http://mystery-box-warehouses-frontend.s3-website-us-east-1.amazonaws.com/product";
    private static final String url = "http://localhost:5173/product";
    private static String productDetailUrl = ""; // to be updated based on first existing product on /product page

    //product cards
    @FindBy(className = "usa-card__container")
    private List<WebElement> cards;

    //below web elements on a product's details page
    @FindBy(id="update-product")
    private WebElement updateProductButton;

    @FindBy(id="update-product-modal")
    private WebElement updateProductModal;

    @FindBy(id="current-product-name")
    private WebElement currentProductName;

    @FindBy(id="current-product-category")
    private WebElement currentProductCategory;

    @FindBy(id="current-product-price")
    private WebElement currentProductPrice;

    //modal form web elements
    @FindBy(css = "input[id='product-name']")
    private WebElement nameField;

    @FindBy(css = "input[id='product-category']")
    private WebElement categoryField;

    @FindBy(css = "input[id='product-price']")
    private WebElement priceField;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    /**
    * Initializes the driver and sets an implicit wait 
    */
    public ProductDetailsPage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * navigating to the product details page from the first existing product
     * pause execution for 1000 mili sec before navigating
     */
    public void get() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // navigate to the list of products page
        this.driver.get(url);

        // if there is atleast one existing product
        if(!cards.isEmpty()){
            // update the url to navigate to the first existing product
            for(WebElement list:cards){
                WebElement existingWarehouseId = list.findElement(By.id("product-id"));
                String idString = existingWarehouseId.getText();
                productDetailUrl = url + "/" + idString.substring(idString.lastIndexOf(":") + 2); // note - "ID: 2" would append '2' to the url
                break;
            }
        }
        else{
            System.out.println("No products exist!");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // navigate to the first existing product's details page
        if(!productDetailUrl.isEmpty()){
            this.driver.get(productDetailUrl);
        }
    }

    /**
    * clicking the update product button
    * pause execution for 1000 mili sec before clicking
    */
    public void clickUpdateProduct() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateProductButton.click();
    }

    /**
    * update product modal is displayed
    */
    public boolean updateProductModalDisplayed() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return updateProductModal.isDisplayed(); 
    }
}