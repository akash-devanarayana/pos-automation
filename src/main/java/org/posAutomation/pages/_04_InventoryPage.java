package org.posAutomation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class _04_InventoryPage {
    // Search the item
    @FindBy(id = "search")
    public WebElement typeTheNameOfSearchItem;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement clickItemSearchButton;

    @FindBy(xpath = "//*[@id='sortable_table']/tbody")
    public WebElement searchResultItemRow;

    // Edit the pricing details of the already added item
    @FindBy(xpath = "(//button[@class='btn btn-more dropdown-toggle'])[5]")
    public WebElement clickItemEditButton;

    @FindBy(xpath = "//*[@id=\"sortable_table\"]/tbody/tr[3]/td[2]/div/ul/li[2]/a")
    public WebElement selectEditPricing;

    @FindBy(xpath = "//*[@id='cost_price']")
    public WebElement clearAndEditProductCost;

    @FindBy(xpath = "//*[@id=\"unit_price\"]")
    public WebElement clearAndEditSeelingPrice;

    @FindBy(xpath = "//*[@id=\"submitf\"]")
    public WebElement pricingPageSaveButton;

    @FindBy(xpath = "/html/body/div[6]/div/div/div[2]/button[2]")
    public WebElement cllickReturnToItemsButton;

    // clear the searched item
    @FindBy(xpath = "//*[@id=\"search_form\"]/div/ul/li[5]/div/a/i")
    public WebElement clearSearchedItem;
}
