package org.posAutomation.tests;

import org.posAutomation.functions.Webby;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WorkOrderTest extends LoginTest {
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
