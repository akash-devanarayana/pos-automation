package org.posAutomation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class _02_DashboardPage {
    @FindBy(xpath = "//*[@id=\"choose_location_modal\"]/div/div/div[2]/ul/li[1]/a")
    public WebElement modalDefaultOption;

    @FindBy(xpath = "//span[text()='Inventory']//parent::a")
    public WebElement sideBarInventoryDropdown;

    @FindBy(xpath = "/html/body/div[3]/div[1]/ul/li[2]/ul/li[1]/a")
    public WebElement sideBarItemOption;

    @FindBy(xpath = "//span[text()='Customers']//parent::a")
    public WebElement sideBarCustomersDropdown;

    @FindBy(xpath = "/html/body/div[3]/div[1]/ul/li[3]/ul/li[1]/a")
    public WebElement sideBarCustomersOption;

    @FindBy(xpath = "//span[text()='Work Orders']//parent::a")
    public WebElement sideBarWorkOrdersDropdown;
}
