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
        Webby.click(customerPage.customersDropdown);
        Thread.sleep(1000);
        Webby.click(customerPage.customersPage);
        Thread.sleep(1000);
        Webby.click(customerPage.addCustomer);
        Thread.sleep(1000);
        Webby.setText(customerPage.firstName, "Test One");
        Thread.sleep(1000);
        Webby.click(customerPage.test);
        Thread.sleep(1000);
        Webby.click(customerPage.testSelect);
        Thread.sleep(1000);
        Webby.click(customerPage.save);
        Thread.sleep(1000);
    }

}
