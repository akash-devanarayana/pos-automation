package org.ucsc.tests;

import org.testng.annotations.Test;
import org.ucsc.framework.BaseTest;
import org.ucsc.framework.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("username", "password");

        // Add assertions to verify login success
    }
}
