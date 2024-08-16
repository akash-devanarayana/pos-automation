package org.posAutomation.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.posAutomation.functions.Webby;
import org.posAutomation.pages.BasePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BasePage {

    @BeforeMethod
    public void setup() {
        new BasePage();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


// Valid Login - using valid username and password
    @Test(priority = 0)
    public void validLoginTest() {

        Webby.setText(loginPage.usernameField, "admin");
        Webby.setText(loginPage.passwordField, "pointofsale");
        Webby.click(loginPage.loginButton);

    // Assertion - verify if successfully logged in

        WebElement dashboardIcon = driver.findElement(By.xpath("//i[@class='icon ti-dashboard']"));
        Assert.assertTrue(dashboardIcon.isDisplayed(), "Couldn't logged in successfully");

    }


// Invalid Login - using valid username & invalid password
    @Test(priority = 1)
    public void invalidPasswordTest(){

            Webby.setText(loginPage.usernameField, "admin");
            Webby.setText(loginPage.passwordField, "invalidPassword");
            Webby.click(loginPage.loginButton);

    // Assertion - verify that the error message is displayed

            WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error']"));
            Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed.");
        }


}

