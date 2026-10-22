package com.matheswar.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NegativeLoginTest {

    @Test
    public void invalidLoginTest() {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Maximize browser
            driver.manage().window().maximize();

            // Step 2: Open website
            driver.get("https://www.saucedemo.com/");

            // Step 3: Enter valid username
            driver.findElement(By.id("user-name"))
                  .sendKeys("standard_user");

            // Step 4: Enter incorrect password
            driver.findElement(By.id("password"))
                  .sendKeys("wrong123");

            // Step 5: Click Login
            driver.findElement(By.id("login-button"))
                  .click();

            // Step 6: Get error message
            String errorMessage = driver.findElement(
                By.cssSelector("[data-test='error']")
            ).getText();

            // Step 7: Verify error message
            Assert.assertTrue(
                errorMessage.contains(
                    "Username and password do not match"
                ),
                "Expected login error message was not displayed"
            );

            System.out.println(
                "Negative login test passed successfully!"
            );

        } finally {
            // Step 8: Close browser
            driver.quit();
        }
    }
}

