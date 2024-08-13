package org.posAutomation.tests;

import org.openqa.selenium.By;
import org.posAutomation.functions.Webby;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkOrderTest extends BaseFunctions {
    public String extractedText;
    public String extractedWorkOrderId;
    public String ranodomString;

    @BeforeClass
    public void setup() throws InterruptedException {
        super.setup();
        super.login();
        Thread.sleep(1000);
        Webby.click(dashboardPage.modalDefaultOption);
        Thread.sleep(1000);
        Webby.click(dashboardPage.sideBarWorkOrdersDropdown);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exp) {
            System.out.println(exp.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        super.tearDown();
    }

    public void searchWorkOrderById(String searchKey) throws InterruptedException {
        Webby.setText(workOrderPage.inputSearch, searchKey);
        Webby.click(workOrderPage.btnSearch);
        Thread.sleep(3000);
    }

    public int genRandomNumber() {
        Random random = new Random();
        return random.nextInt(90000) + 10000;
    }

    @Test
    public void addNewWorkOrder() throws InterruptedException {

        Webby.click(workOrderPage.btnNewWorkOrder);

        //Wait till NewWorkOrder pop up loads
        Thread.sleep(1000);

        Webby.setText(workOrderPage.inputCustomer, "Paul Walker");
        Webby.click(workOrderPage.searchResult_Customer);

        Webby.setText(workOrderPage.inputItem, "Nissan Skyline");
        Webby.click(workOrderPage.searchResult_Item);

        Thread.sleep(2500);
        Webby.click(workOrderPage.btnSave_NewWorkOrder);

        //Wait till WorkOrderEdit page loads
        Thread.sleep(5000);

        //Extracting the work order id from WorkOrderEdit page
        extractedText = driver.findElement(By.xpath("//div[@class='work_order_id']")).getText();

        String regex = "\\d+"; // Regular expression to match the number
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(extractedText);
        if (matcher.find()) {
            extractedWorkOrderId = matcher.group();
            System.out.println("Extracted Text: " + extractedText);
            System.out.println("Work Order #: " + extractedWorkOrderId);
        } else {
            System.out.println("Number not found");
        }
        Webby.click(workOrderPage.btnDone_WorkOrderEdit);

        searchWorkOrderById(extractedWorkOrderId);

        //Extract the search result
        String customerFirstName = Webby.getText(workOrderPage.firstSearchResult_customerFirstName);
        assert customerFirstName != null;
        String customerLastName = Webby.getText(workOrderPage.firstSearchResult_customerLastName);
        assert customerLastName != null;
        String itemName = Webby.getText(workOrderPage.firstSearchResult_itemName);
        assert itemName != null;
        System.out.println("Found search result: " + customerFirstName + " " + customerLastName + "'s " + itemName);

        boolean status = customerFirstName.equals("Paul") & customerLastName.equals("Walker") & itemName.equals("Nissan Skyline");
        Assert.assertTrue(status);
    }

    @Test(dependsOnMethods = {"addNewWorkOrder"})
    public void deleteExistingWorkOrder() throws InterruptedException {
        //extractedWorkOrderId = "114";

        searchWorkOrderById(extractedWorkOrderId);

        //Extract the search result
        String customerFirstName2 = Webby.getText(workOrderPage.firstSearchResult_customerFirstName);
        System.out.println(customerFirstName2);
        assert customerFirstName2 != null;
        String customerLastName2 = Webby.getText(workOrderPage.firstSearchResult_customerLastName);
        assert customerLastName2 != null;
        String itemName2 = Webby.getText(workOrderPage.firstSearchResult_itemName);
        assert itemName2 != null;
        System.out.println("Found search result: " + customerFirstName2 + " " + customerLastName2 + "'s " + itemName2);

        boolean statusBeforeDeletion = customerFirstName2.equals("Paul") & customerLastName2.equals("Walker") & itemName2.equals("Nissan Skyline");
        System.out.println("Found the searched Work Order? " + statusBeforeDeletion);

        if (statusBeforeDeletion) {
            Webby.click(workOrderPage.selectTheFirstSearchResult);
            Thread.sleep(500); //wait till the Delete button appears
            Webby.click(workOrderPage.btnDelete);
            Thread.sleep(1000); //wait till delete confirmation prompt appears
            Webby.click(workOrderPage.btnOK_DeleteConfirmation);
            Thread.sleep(2000); //wait till page refresh
            System.out.println("Work Order " + extractedWorkOrderId + " deleted");

            //Confirm the deletion
            searchWorkOrderById(extractedWorkOrderId);

            String searchResult = Webby.getText(workOrderPage.noSearchResultWarning);
            assert searchResult != null;
            Assert.assertEquals(searchResult, "No Work Orders");
        }
    }


    @Test
    public void addNewWorkOrderWithNewCustomer() throws InterruptedException {

        Webby.click(workOrderPage.btnNewWorkOrder);

        Webby.click(workOrderPage.btnAddNewCustomer);
        Thread.sleep(2000);
        Webby.setText(workOrderPage.inputFirstName, "Dominic Toretto");
        Thread.sleep(700);
        ranodomString = String.valueOf(genRandomNumber());
        Webby.setText(workOrderPage.inputLastName, ranodomString);
        Thread.sleep(700);
        Webby.setText(workOrderPage.inputEmail, "dtoretto" + ranodomString + "@fnf.com");
        Thread.sleep(700);
        Webby.click(workOrderPage.btnSave_NewCustomer);
        Thread.sleep(700);
        Webby.click(workOrderPage.btnAddCustomerToWorkOrder);
        Thread.sleep(3000);

        //Wait till NewWorkOrder pop up loads
        Thread.sleep(2000);

        Webby.setText(workOrderPage.inputItem, "Dodge Charger");
        Webby.click(workOrderPage.searchResult_Item);

        Thread.sleep(2000);
        Webby.click(workOrderPage.btnSave_NewWorkOrder);

        //Wait till WorkOrderEdit page loads
        Thread.sleep(5000);

        //Extracting the work order id from WorkOrderEdit page
        extractedText = driver.findElement(By.xpath("//div[@class='work_order_id']")).getText();

        String regex = "\\d+"; // Regular expression to match the number
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(extractedText);
        if (matcher.find()) {
            extractedWorkOrderId = matcher.group();
            System.out.println("Extracted Text: " + extractedText);
            System.out.println("Work Order #: " + extractedWorkOrderId);
        } else {
            System.out.println("Number not found");
        }
        Webby.click(workOrderPage.btnDone_WorkOrderEdit);

        searchWorkOrderById(extractedWorkOrderId);

        //Extract the search result
        String customerFirstName = Webby.getText(workOrderPage.firstSearchResult_customerFirstName);
        assert customerFirstName != null;
        String customerLastName = Webby.getText(workOrderPage.firstSearchResult_customerLastName);
        assert customerLastName != null;
        String itemName = Webby.getText(workOrderPage.firstSearchResult_itemName);
        assert itemName != null;
        System.out.println("Found search result: " + customerFirstName + " " + customerLastName + "'s " + itemName);

        boolean status = customerFirstName.equals("Dominic Toretto") & customerLastName.equals(ranodomString) & itemName.equals("Dodge Charger");
        Assert.assertTrue(status);
    }


}
