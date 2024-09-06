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

public class ProductPage {
    private final WebDriver driver;
    private static final String url = "http://mystery-box-warehouses-frontend.s3-website-us-east-1.amazonaws.com/product";
    // private static final String url = "http://localhost:5173/product";
    //navbar
    @FindBy(id="nav-product")
    private WebElement goToProductsPage;

    @FindBy(id="nav-warehouse")
    private WebElement goToWarehousesPage;
    
    //buttons
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
     * select sort by products
     * pause execution for 1000 mili sec before navigating
     */
    public void selectSortBy(String selectOption) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Select select = new Select(sortProduct);
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
                WebElement pID = list.findElement(By.id("product-id"));
                int current = Integer.parseInt(pID.getText().substring(pID.getText().lastIndexOf(":") + 2));
                if(current <= prev){
                    return false;
                }
                else{
                    prev = current;
                }
            }
        }
        // sort by price
        else if(sortOption.equals("By Price"))
        {
            for(WebElement list:cards){
                WebElement pPrice = list.findElement(By.id("product-price"));
                int current = Integer.parseInt(pPrice.getText().substring(1));
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
     * click add product button
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
                Actions actions = new Actions(driver);
                actions.moveToElement(deleteButton).click().perform();
                // deleteButton.click();
                break;
            }
        }
    }

}
