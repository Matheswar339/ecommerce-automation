package com.matheswar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPages {

    private WebDriver driver;

    // Product page locators
    private By backpackAddButton =
            By.id("add-to-cart-sauce-labs-backpack");

    private By cartButton =
            By.className("shopping_cart_link");

    // Constructor
    public ProductsPages(WebDriver driver) {
        this.driver = driver;
    }

    // Add product to cart
    public void addBackpackToCart() {
        driver.findElement(backpackAddButton).click();
    }

    // Open shopping cart
    public void openCart() {
        driver.findElement(cartButton).click();
    }
}

