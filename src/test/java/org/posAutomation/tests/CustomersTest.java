package org.posAutomation.tests;

import org.posAutomation.functions.Webby;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomersTest extends LoginTest {

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
    public void addCustomer() throws InterruptedException {
        Webby.click(dashboardPage.sideBarCustomersDropdown);
        Webby.click(dashboardPage.sideBarCustomersOption);
        Webby.click(customerPage.addCustomer);
        Webby.setText(customerPage.firstName, "Test One");
        Webby.click(customerPage.test);
        Webby.click(customerPage.testSelect);
        Webby.click(customerPage.save);
    }
}
