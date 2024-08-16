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

    //valid username and password
    @Test
    public void validLoginTest() {
        Webby.setText(loginPage.usernameField, "admin");
        Webby.setText(loginPage.passwordField, "pointofsale");
        Webby.click(loginPage.loginButton);
    }

    //valid pw , invalid username
    @Test
    public void invalidUsernameTest() {
        Webby.setText(loginPage.usernameField, "invalidadmin");
        Webby.setText(loginPage.passwordField, "pointofsale");
        Webby.click(loginPage.loginButton);

        //assertion
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed for invalid username.");
    }

    //valid username, invalid pw
    @Test
    public void invalidPasswordTest() {
        Webby.setText(loginPage.usernameField, "admin");
        Webby.setText(loginPage.passwordField, "wrongPassword");
        Webby.click(loginPage.loginButton);

        //assertion
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed for invalid password.");
    }

    //Invalid username and password
    @Test
    public void invalidUsernameAndPasswordTest() {
        Webby.setText(loginPage.usernameField, "InvalidAdmin");
        Webby.setText(loginPage.passwordField, "wrongPassword");
        Webby.click(loginPage.loginButton);

        //assertion
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed for invalid username and password.");
    }

    //Empty username
    @Test
    public void emptyUsernameTest() {
        Webby.setText(loginPage.usernameField, "");
        Webby.setText(loginPage.passwordField, "pointofsale");
        Webby.click(loginPage.loginButton);

        //assertion
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed for empty username.");
    }

    //Empty password
    @Test
    public void emptyPasswordTest() {
        Webby.setText(loginPage.usernameField, "admin");
        Webby.setText(loginPage.passwordField, "");
        Webby.click(loginPage.loginButton);

        //assertion
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed for empty password.");
    }
}

