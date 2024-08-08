package org.posAutomation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class _05_WorkOrderPage {
    //*************** Choose Location pop up window ***************
    @FindBy(xpath = "//*[@id=\"choose_location_modal\"]/div/div/div[2]/ul/li[1]/a")
    public WebElement chooseLocation_Default;

    //*************** Side navigation panel ***************
    @FindBy(xpath = "//i[@class='ion-hammer']")
    public WebElement iconWorkOrder;

    //*************** WorkOrder page ***************
    @FindBy(xpath = "//a[@id='new_work_order_btn']")
    public WebElement btnNewWorkOrder;

    //*************** NewWorkOrder pop up window ***************
    @FindBy(xpath = "//span[text()='×']")
    public WebElement btnClose_NewWorkOrder;

    @FindBy(xpath = "//input[@id='customer']")
    public WebElement inputCustomer;

    @FindBy(xpath = "//a[@id='new-customer']")
    public WebElement btnAddNewCustomer;

    @FindBy(xpath = "//input[@id='item']")
    public WebElement inputItem;

    @FindBy(xpath = "//a[@id='new-item']")
    public WebElement btnAddNewItem;

    @FindBy(xpath = "//a[@id='add_generic_item']")
    public WebElement btnAddAsRepairItem;

    @FindBy(xpath = "//input[@id='sale_item_notes_save_btn']")
    public WebElement btnSave_NewWorkOrder;

    @FindBy(xpath = "//input[@value='Cancel']")
    public WebElement btnCancel_NewWorkOrder;

    //*************** Search Result ***************
    @FindBy(xpath = "//div[@class='name' and text()='Paul Walker']")
    public WebElement searchResult_Customer;

    @FindBy(xpath = "//div[@class='name' and text()='Nissan Skyline (Vehicle) - $0.00']")
    public WebElement searchResult_Item;

    //*************** Description pop up window ***************
    @FindBy(xpath = "//button[@class='bootbox-close-button close' and text()='×']")
    public WebElement btnClose_Description;

    @FindBy(xpath = "//input[@class='bootbox-input bootbox-input-text form-control' and @type='text']")
    public WebElement inputDescription;

    @FindBy(xpath = "//button[text()='OK']")
    public WebElement btnOk_Description;

    @FindBy(xpath = "//button[text()='Cancel']")
    public WebElement btnCancel_Description;
}
