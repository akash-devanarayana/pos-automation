package org.posAutomation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class _05_WorkOrderPage {

//    //*************** Choose Location pop up window ***************
//    @FindBy(xpath = "//*[@id=\"choose_location_modal\"]/div/div/div[2]/ul/li[1]/a")
//    public WebElement chooseLocation_Default;
//
//    //*************** Side navigation panel ***************
//    //@FindBy(xpath = "//i[@class='ion-hammer']")
//    @FindBy(xpath = "//span[text()='Work Orders']")
//    public WebElement iconWorkOrder;

    //*************** WorkOrder page ***************
    @FindBy(xpath = "//input[@id='search']")
    public WebElement inputSearch;

    @FindBy(xpath = "//span[@class='ion-ios-search-strong']")
    public WebElement btnSearch;

    @FindBy(xpath = "//a[@id='new_work_order_btn']")
    public WebElement btnNewWorkOrder;

    @FindBy(xpath = "//a[@id='delete']")
    public WebElement btnDelete; //this button appears when select a work order

    @FindBy(xpath = "//button[text()='OK']")
    public WebElement btnOK_DeleteConfirmation; //this button appears on work order delete confirmation popup

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

    @FindBy(xpath = "//div[@class='name' and contains(text(), 'Car')]")
    public WebElement searchResult_Item;

//    @FindBy(xpath = "//div[@class='name' and text()='Paul Walker']")
//    public WebElement searchResult_Customer;
//
//    @FindBy(xpath = "//div[@class='name' and starts-with(text(), 'Nissan Skyline')]")
//    public WebElement searchResult_Item;

    @FindBy(xpath = "//td[@data-column_name='select_checkbox']")
    public WebElement selectTheFirstSearchResult;

    @FindBy(xpath = "//span[@class='text-warning']")
    public WebElement noSearchResultWarning;

    @FindBy(xpath = "//*[@id=\"sortable_table\"]/tbody/tr/td[10]")
    public WebElement firstSearchResult_customerFirstName;

    @FindBy(xpath = "//*[@id=\"sortable_table\"]/tbody/tr/td[11]")
    public WebElement firstSearchResult_customerLastName;

    @FindBy(xpath = "//*[@id=\"sortable_table\"]/tbody/tr/td[12]")
    public WebElement firstSearchResult_itemName;

    //*************** Description pop up window ***************
    @FindBy(xpath = "//button[@class='bootbox-close-button close' and text()='×']")
    public WebElement btnClose_Description;

    @FindBy(xpath = "//input[@class='bootbox-input bootbox-input-text form-control' and @type='text']")
    public WebElement inputDescription;

    @FindBy(xpath = "//button[text()='OK']")
    public WebElement btnOk_Description;

    @FindBy(xpath = "//button[text()='Cancel']")
    public WebElement btnCancel_Description;

    //***************  WorkOrder Edit page ***************
    @FindBy(xpath = "//a[@id='done_btn']")
    public WebElement btnDone_WorkOrderEdit;

    //***************  New Customer page ***************
    @FindBy(xpath = "//input[@id='first_name']")
    public WebElement inputFirstName;

    @FindBy(xpath = "//input[@id='last_name']")
    public WebElement inputLastName;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement inputEmail;

    @FindBy(xpath = "//input[@name='submitf']")
    public WebElement btnSave_NewCustomer;

    @FindBy(xpath = "//button[text()='Add Customer to Work Order']")
    public WebElement btnAddCustomerToWorkOrder;
}
