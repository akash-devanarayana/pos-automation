package org.ucsc.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.ucsc.framework.pages.BasePage;
import org.ucsc.framework.pages._01_LoginPage;

public class LoginTest extends BasePage {

    private _01_LoginPage loginPage;

    @BeforeTest
    private void setup() {
        setUp();
        loginPage = new _01_LoginPage();
        PageFactory.initElements(driver, loginPage);
    }

    @AfterTest
    private void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void ValidLoginTest() {
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("admin");
//        loginPage.passwordField.clear();
//        loginPage.passwordField.sendKeys("admin");
        loginPage.loginButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.phppointofsale.com/index.php/home/index/1");
    }
}
