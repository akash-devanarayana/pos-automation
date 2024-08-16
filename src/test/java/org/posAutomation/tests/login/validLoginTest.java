package org.posAutomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.posAutomation.functions.Webby;
import org.posAutomation.pages.BasePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class validLoginTest extends BasePage {

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
    @Test
    public void validLoginTest() {

        SoftAssert softAssert = new SoftAssert();

        Webby.setText(loginPage.usernameField, "admin");
        Webby.setText(loginPage.passwordField, "hfcf");
        Webby.click(loginPage.loginButton);

        // Assertion - verify if successfully logged in

        WebElement dashboardIcon = driver.findElement(By.xpath("//i[@class='icon ti-dashboard']"));
        softAssert.assertTrue(dashboardIcon.isDisplayed(), "Couldn't logged in successfully");

        softAssert.assertAll();

    }


}

