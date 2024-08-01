package org.ucsc.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.ucsc.framework.pages._01_LoginPage;

public class LoginTest extends BaseTest {

    private _01_LoginPage loginPage;

    @BeforeTest
    private void setup() {
        loginPage = new _01_LoginPage();
        PageFactory.initElements(driver, loginPage);
    }

    @Test
    public void ValidLoginTest() {
        loginPage.usernameField.sendKeys("Admin");
        loginPage.passwordField.sendKeys("Admin");
        loginPage.loginButton.click();
        // Add assertions to verify login success
    }
}
