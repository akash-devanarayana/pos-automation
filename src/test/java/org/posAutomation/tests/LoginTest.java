package org.posAutomation.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.posAutomation.pages.BasePage;
import org.posAutomation.pages._01_LoginPage;

public class LoginTest extends BasePage {

    private _01_LoginPage loginPage;

    @BeforeTest
    private void setup() {
        init();
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
    }
}
