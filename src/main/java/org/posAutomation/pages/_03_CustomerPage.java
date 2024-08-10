package org.posAutomation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class _03_CustomerPage {

    @FindBy(xpath = "//*[@id=\"mainMenu\"]/li[3]/a/i")
    public WebElement customersDropdown;

    @FindBy(xpath = "//*[@id=\"mainMenu\"]/li[3]/ul/li[1]/a/i")
    public WebElement customersPage;

    @FindBy(xpath = "//*[@id=\"new-person-btn\"]/span")
    public WebElement addCustomer;

    @FindBy(xpath = " //*[@id=\"first_name\"]")
    public WebElement firstName;

    @FindBy(xpath = "//*[@id=\"customer_form\"]/div/div[2]/div[18]/div/select")
    public WebElement test;

    @FindBy(xpath = "//*[@id=\"customer_form\"]/div/div[2]/div[18]/div/select/option[2]")
    public WebElement testSelect;

    @FindBy(xpath = "//*[@id=\"submitf\"]")
    public WebElement save;


}
