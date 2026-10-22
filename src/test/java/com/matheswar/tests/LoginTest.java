package com.matheswar.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.matheswar.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {

    @Test
    public void validLoginTest() {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            driver.get("https://www.saucedemo.com/");

            // Create LoginPage object
            LoginPage loginPage = new LoginPage(driver);

            // Reuse the login method from LoginPage
            loginPage.login("standard_user", "secret_sauce");

            // Verify successful login
            Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "Login failed"
            );

            System.out.println("POM Login Test Passed!");

        } finally {
            driver.quit();
        }
    }
}


