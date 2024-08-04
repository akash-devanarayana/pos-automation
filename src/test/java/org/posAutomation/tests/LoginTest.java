package org.posAutomation.tests;

import org.openqa.selenium.support.PageFactory;
import org.posAutomation.functions.Webby;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.posAutomation.pages.BasePage;
import org.posAutomation.pages._01_LoginPage;

public class LoginTest extends BasePage {

    private _01_LoginPage loginPage;

    @BeforeTest
    void setup() {
        init();
        loginPage = new _01_LoginPage();
        PageFactory.initElements(driver, loginPage);
    }

    @Test
    public void ValidLoginTest() {
        Webby.setText(loginPage.usernameField, "admin");
        Webby.setText(loginPage.passwordField, "pointofsale");
        Webby.click(loginPage.loginButton);
    }

  /*  @AfterTest
    private void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }*/

}
