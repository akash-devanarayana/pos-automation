package org.posAutomation.tests;

import org.openqa.selenium.support.PageFactory;
import org.posAutomation.functions.Webby;
import org.posAutomation.pages._01_LoginPage;
import org.posAutomation.pages._05_WorkOrderPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;
import org.posAutomation.functions.Webby;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.posAutomation.pages.BasePage;
import org.posAutomation.pages._05_WorkOrderPage;

public class WorkOrderTest extends BasePage {

    private _01_LoginPage loginPage;
    private _05_WorkOrderPage workOrderPage;

    @BeforeTest
    private void setup() throws InterruptedException {
        init();

        loginPage = new _01_LoginPage();
        PageFactory.initElements(driver, loginPage);
        workOrderPage = new _05_WorkOrderPage();
        PageFactory.initElements(driver, workOrderPage);

        Webby.click(loginPage.loginButton);
        Webby.click(workOrderPage.chooseLocation_Default);
        Thread.sleep(3000);
    }

    @AfterTest
    private void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }



        @Test
    public void AddNewWorkOrder_TC01() {
        Webby.click(workOrderPage.iconWorkOrder);
        Webby.click(workOrderPage.btnNewWorkOrder);
        Webby.setText(workOrderPage.inputCustomer, "Paul Walker");
        Webby.click(workOrderPage.searchResult_Customer);
        Webby.setText(workOrderPage.inputItem, "Nissan Skyline");
        Webby.click(workOrderPage.searchResult_Item);

        //NEED TO VALIDATE THE ABOVE ADDED WORK ORDER
    }

}
