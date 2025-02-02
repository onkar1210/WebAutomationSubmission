package PoPackage;

import BasePackage.ChildBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class HomePagePO extends ChildBase {

    public By search_box = By.xpath("//input[@placeholder='Search for anything']");
    public By search_button = By.xpath("//button/span[text()='Search']");
    public By first_product_from_search_result = By.xpath("(//ul[contains(@class,'srp-results')]//span[@role='heading'])[1]");
    public By add_to_cart_link = By.xpath("//*[text()='Add to cart']");
    public By count_of_cart_item_on_icon = By.xpath("//*[contains(@class,'badge') and @role='img']");




    public void searchProduct(String product){  driver.findElement(search_box).sendKeys(product);   }

    public void clickOnSearchButton(){  driver.findElement(search_button).click();   }

    public String getTitleOfThePage(){  return driver.getTitle().trim();    }

    public void clickOnFirstProductFromResult(){
        driver.findElement(first_product_from_search_result).click();
    }

    public void clickOnAddToCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(add_to_cart_link));
        driver.findElement(add_to_cart_link).click();
    }

    public String getTheCountOfAttemsInTheCart(){
        return driver.findElement(count_of_cart_item_on_icon).getText().trim();
    }


}
