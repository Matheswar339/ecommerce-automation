package com.matheswar.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RemoveFromCartTest {

    @Test
    public void removeProductFromCartTest() {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Open browser
            driver.manage().window().maximize();

            // Step 2: Open SauceDemo
            driver.get("https://www.saucedemo.com/");

            // Step 3: Login
            driver.findElement(By.id("user-name"))
                  .sendKeys("standard_user");

            driver.findElement(By.id("password"))
                  .sendKeys("secret_sauce");

            driver.findElement(By.id("login-button"))
                  .click();

            // Step 4: Verify login
            Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "Login failed"
            );

            // Step 5: Add backpack to cart
            driver.findElement(
                By.id("add-to-cart-sauce-labs-backpack")
            ).click();

            // Step 6: Open shopping cart
            driver.findElement(By.className("shopping_cart_link"))
                  .click();

            // Step 7: Verify product is in cart
            Assert.assertTrue(
                driver.findElements(By.id("item_4_title_link"))
                      .size() > 0,
                "Product was not added to cart"
            );

            // Step 8: Remove product
            driver.findElement(
                By.id("remove-sauce-labs-backpack")
            ).click();

            // Step 9: Verify product is removed
            Assert.assertEquals(
                driver.findElements(By.id("item_4_title_link"))
                      .size(),
                0,
                "Product was not removed from cart"
            );

            System.out.println(
                "Remove from Cart test passed successfully!"
            );

        } finally {
            // Close browser after test
            driver.quit();
        }
    }
}

