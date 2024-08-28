package org.posAutomation.functions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.posAutomation.pages.BasePage;

import static org.posAutomation.utils.Constants.TIMEOUT;
import static org.posAutomation.utils.Utils.smartWait;

public class Webby {

    private static final WebDriver driver = BasePage.driver;

    /**
     * Clicks on the specified WebElement after waiting for it to be clickable.
     *
     * @param element the WebElement to be clicked
     */
    public static void click(WebElement element) {
        try {
            smartWait(driver, TIMEOUT, element);
            element.click();
        } catch (Exception e) {
            // Log the error or handle it appropriately
            System.out.println("Error clicking element: " + e.getMessage());
        }
    }

    /**
     * Sets text to the specified WebElement after waiting for it to be visible.
     *
     * @param element   the WebElement where text will be set
     * @param textToSet the text to be set in the WebElement
     */
    public static void setText(WebElement element, String textToSet) {
        try {
            smartWait(driver, TIMEOUT, element);
            element.clear();
            element.sendKeys(textToSet);
        } catch (Exception e) {
            // Log the error or handle it appropriately
            System.out.println("Error setting text: " + e.getMessage());
        }
    }


    //To extract a text from a web element
    public static String getText(WebElement element) {
        try {
            smartWait(driver, TIMEOUT, element);
            return element.getText();
        } catch (Exception e) {
            // Log the error or handle it appropriately
            System.out.println("Error extracting text: " + e.getMessage());
            return null;
        }
    }

}
