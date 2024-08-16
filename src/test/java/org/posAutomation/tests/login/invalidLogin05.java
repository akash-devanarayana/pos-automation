package org.posAutomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.posAutomation.functions.Webby;
import org.posAutomation.pages.BasePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class invalidLogin05 extends BasePage {

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



    // Invalid Login - using empty password
    @Test
    public void invalidPasswordTest(){

        Webby.setText(loginPage.usernameField, "admin");
        Webby.setText(loginPage.passwordField, "");
        Webby.click(loginPage.loginButton);

        // Assertion - verify that the error message is displayed

        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed.");
    }


}

