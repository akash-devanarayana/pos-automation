package org.posAutomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.posAutomation.utils.Constants.LOGIN_PAGE_URL;

public class BasePage {
    protected WebDriver driver;

    public void init() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(LOGIN_PAGE_URL);
    }
}
