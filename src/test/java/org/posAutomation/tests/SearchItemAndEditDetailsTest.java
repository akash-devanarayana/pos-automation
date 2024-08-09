package org.posAutomation.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.posAutomation.functions.Webby;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchItemAndEditDetailsTest extends LoginTest {

    @BeforeMethod
    public void setup() {
        super.setup();
        validLoginTest();
        Webby.click(dashboardPage.modalDefaultOption);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exp) {
            System.out.println(exp.getMessage());
        }
    }

    @Test(priority = 1)
    public void goToItemPageAndSearch() {
        // Navigate to item page
        Webby.click(dashboardPage.sideBarInventoryDropdown);
        Webby.click(dashboardPage.sideBarItemOption);

        // Search an item from the item list
        Webby.setText(inventoryPage.typeTheNameOfSearchItem, "Testitem");
        Webby.click(inventoryPage.clickItemSearchButton);

        // Wait for the search input show slowly
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait for the search results to be present
        wait.until(ExpectedConditions.visibilityOfAllElements(inventoryPage.searchResultItemRow));
    }

    @Test(priority = 2)
    public void editItem() {
        // Navigate to item page
        Webby.click(dashboardPage.sideBarInventoryDropdown);
        Webby.click(dashboardPage.sideBarItemOption);

        //Edit the existing record
        Webby.click(inventoryPage.clickItemEditButton);

        //Select as Edit price from drop down
        Webby.click(inventoryPage.selectEditPricing);

        //Clear and edit cost price without tax
        Webby.setText(inventoryPage.clearAndEditProductCost, ("100.00"));

        //Clear and edit selling price
        Webby.setText(inventoryPage.clearAndEditSeelingPrice, ("200"));

        //Pricing page save button click and save
        Webby.click(inventoryPage.pricingPageSaveButton);

        //Return to items list - clicking "Return to items" button on the pop-up msg
        Webby.click(inventoryPage.cllickReturnToItemsButton);

        //Clear searched items
        Webby.click(inventoryPage.clearSearchedItem);
    }
}




