package com.matheswar.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckoutTest {

    @Test
    public void checkoutTest() {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        // Keep browser open for manual inspection
        try {
            driver.manage().window().maximize();

            // Step 1: Open website
            driver.get("https://www.saucedemo.com/");

            // Step 2: Login
            driver.findElement(By.id("user-name"))
                  .sendKeys("standard_user");

            driver.findElement(By.id("password"))
                  .sendKeys("secret_sauce");

            driver.findElement(By.id("login-button"))
                  .click();

            // Step 3: Verify login
            Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "Login failed"
            );

            // Step 4: Add product to cart
            driver.findElement(
                By.id("add-to-cart-sauce-labs-backpack")
            ).click();

            // Step 5: Open cart
            driver.findElement(By.className("shopping_cart_link"))
                  .click();

            // Step 6: Verify product in cart
            Assert.assertTrue(
                driver.findElements(By.id("item_4_title_link"))
                      .size() > 0,
                "Product not found in cart"
            );

            // Step 7: Click Checkout
            driver.findElement(By.id("checkout")).click();

            // Step 8: Enter customer details
            driver.findElement(By.id("first-name"))
                  .sendKeys("Matheswar");

            driver.findElement(By.id("last-name"))
                  .sendKeys("R");

            driver.findElement(By.id("postal-code"))
                  .sendKeys("600001");

            // Step 9: Continue
            driver.findElement(By.id("continue")).click();

            // Step 10: Verify checkout overview page
            Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-two.html"),
                "Checkout overview page did not open"
            );

            System.out.println(
                "Checkout test passed successfully!"
            );

        } catch (Exception e) {
            System.out.println("Checkout test failed: " + e.getMessage());
            throw e;
        }

        // Browser intentionally remains open for manual inspection.
        // Close Chrome manually when finished.
    }
}
