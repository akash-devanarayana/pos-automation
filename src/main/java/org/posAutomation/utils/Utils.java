package org.posAutomation.utils;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utils {
    public static void smartWait(WebDriver driver, int timeout, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException exception) {
            System.out.println("Element Not Found!");
        }
    }
}
