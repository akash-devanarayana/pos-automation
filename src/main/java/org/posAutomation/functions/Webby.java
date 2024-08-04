package org.posAutomation.functions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.posAutomation.pages.BasePage;

import static org.posAutomation.utils.Constants.TIMEOUT;
import static org.posAutomation.utils.Utils.smartWait;

public class Webby extends BasePage {
    public static void click(WebElement element) {
        smartWait(driver, TIMEOUT, element);
        element.click();
    }

    public static void setText(WebElement element, String textToSet) {
        smartWait(driver, TIMEOUT, element);
        element.clear();
        element.sendKeys(textToSet);
    }

   /*public static void selectCheckbox(WebElement checkbox) {
       smartWait(driver, TIMEOUT, checkbox);
       if (!checkbox.isSelected()) {
           checkbox.click();
       }
   }*/

        public static void clear(WebElement element) {
            element.clear();
        }





    }


