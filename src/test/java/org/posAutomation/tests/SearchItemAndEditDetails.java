package org.posAutomation.tests;

import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.posAutomation.functions.Webby;
import org.posAutomation.pages._04_InventoryPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchItemAndEditDetails extends LoginTest {

    private _04_InventoryPage inventoryPage;

    @BeforeTest
    public void setup() {
        super.setup();
        inventoryPage = new _04_InventoryPage();
        PageFactory.initElements(driver, inventoryPage);
    }


    @Test(priority = 1)
    public void goToItemPageAndSearch() {

        //Navigate to item page
        driver.get("https://demo.phppointofsale.com/index.php/items");


        //Search a item form the item list
        Webby.setText(inventoryPage.typeTheNameOfSearchItem, "Testitem");
        Webby.click(inventoryPage.clickItemSearchButton);

        // Wait for the search input show slowly
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(inventoryPage.typeTheNameOfSearchItem));

        // Wait for the search results to be present
        wait.until(ExpectedConditions.visibilityOfAllElements(inventoryPage.searchResultItemRow));

    }

        @Test (priority = 2)
        public void editItem()

    {

        //Edit the existing record
        Webby.click(inventoryPage.clickItemEditButton);


        //Select as Edit price from drop down
        Webby.click(inventoryPage.selectEditPricing);

        //Clear and edit cost price without tax
        Webby.clear(inventoryPage.clearAndEditProductCost);
        Webby.setText(inventoryPage.clearAndEditProductCost,("100.00"));

        //Clear and edit seeling price
        Webby.clear(inventoryPage.clearAndEditSeelingPrice);
        Webby.setText(inventoryPage.clearAndEditSeelingPrice,("200"));

        //Pricing page save button click and save
        Webby.click(inventoryPage.pricingPageSaveButton);

        //Return to items list - clicking "Return to items" button on the pop up msg
        Webby.click(inventoryPage.cllickReturnToItemsButton);

        //Clera serached items
        Webby.click(inventoryPage.clearSearchedItem);


    }

    @AfterTest
    private void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }


}




