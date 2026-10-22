package com.matheswar.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.matheswar.pages.LoginPage;
import com.matheswar.pages.ProductsPages;
import com.matheswar.pages.CheckoutPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckoutCompleteTest {

    @Test
    public void completeOrderTest() {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            // Step 1: Open website
            driver.get("https://www.saucedemo.com/");

            // Step 2: Login using POM
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("standard_user", "secret_sauce");

            // Step 3: Add product to cart
            ProductsPages productsPage = new ProductsPages(driver);
            productsPage.addBackpackToCart();
            productsPage.openCart();

            // Step 4: Start checkout
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.clickCheckout();

            // Step 5: Enter customer details
            checkoutPage.enterCustomerDetails(
                "Matheswar", "R", "600001"
            );

            checkoutPage.clickContinue();

            // Step 6: Verify checkout overview
            Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-two.html"),
                "Checkout overview page did not open"
            );

            // Step 7: Finish order
            checkoutPage.finishOrder();

            // Step 8: Verify confirmation message
            Assert.assertEquals(
                checkoutPage.getConfirmationMessage(),
                "Thank you for your order!",
                "Order confirmation message is incorrect"
            );

            System.out.println("POM Checkout Test Passed!");

        } finally {
            driver.quit();
        }
    }
}

