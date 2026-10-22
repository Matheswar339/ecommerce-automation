package com.matheswar.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.matheswar.pages.LoginPage;
import com.matheswar.pages.ProductsPages;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddToCartTest {

    @Test
    public void addProductToCartTest() {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            // Step 1: Open website
            driver.get("https://www.saucedemo.com/");

            // Step 2: Login using LoginPage
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("standard_user", "secret_sauce");

            // Step 3: Add product using ProductsPage
            ProductsPages productsPage = new ProductsPages(driver);
            productsPage.addBackpackToCart();

            // Step 4: Open cart
            productsPage.openCart();

            // Step 5: Verify product is in cart
            String cartProduct = driver.findElement(
                org.openqa.selenium.By.className("inventory_item_name")
            ).getText();

            Assert.assertEquals(
                cartProduct,
                "Sauce Labs Backpack",
                "Product was not added to cart"
            );

            System.out.println("POM Add to Cart Test Passed!");

        } finally {
            // Close browser after test
            driver.quit();
        }
    }
}

