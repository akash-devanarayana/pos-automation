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

    @Test //TC#01 Prerequisite: Customer:"Paul Walker" & Item:"Nissan Skyline" should be available in the system
    public void addNewWorkOrder() throws InterruptedException {
        System.out.println("TC#01: addNewWorkOrder started");

        Webby.click(workOrderPage.btnNewWorkOrder);
        Thread.sleep(1000); //Wait till NewWorkOrder pop up loads

        Webby.setText(workOrderPage.inputCustomer, "Paul Walker");
        Thread.sleep(300);
        Webby.click(workOrderPage.searchResult_Customer);

        Webby.setText(workOrderPage.inputItem, "Nissan Skyline");
        Thread.sleep(300);
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
            System.out.println("Work Order ID not found");
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

        System.out.println("TC#01: addNewWorkOrder completed");
    }

    @Test(dependsOnMethods = {"addNewWorkOrder"}) //TC#07
    public void deleteExistingWorkOrder() throws InterruptedException {
        System.out.println("TC#07: deleteExistingWorkOrder started");

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
            System.out.println("Work Order " + extractedWorkOrderId + " deleted");

            //Extract the error message
            Thread.sleep(500); //wait till the deletion success message appears
            String extractedSuccessMessageTitle = Webby.getText(workOrderPage.toastMessageTitle);
            assert extractedSuccessMessageTitle != null;
            String extractedSuccessMessageBody = Webby.getText(workOrderPage.toastMessageBody);
            assert extractedSuccessMessageBody != null;
            System.out.println("Extracted message: " + extractedSuccessMessageTitle + " " + extractedSuccessMessageBody);

            boolean status = extractedSuccessMessageTitle.equals("Success") & extractedSuccessMessageBody.equals("You have successfully deleted 1 Work order(s)");
            Assert.assertTrue(status);

            Thread.sleep(2000); //wait till page refresh

            //Confirm the deletion
            searchWorkOrderById(extractedWorkOrderId);

            String searchResult = Webby.getText(workOrderPage.noSearchResultWarning);
            assert searchResult != null;
            Assert.assertEquals(searchResult, "No Work Orders");
            System.out.println("Work Order " + extractedWorkOrderId + " deletion confirmed");
        }

        System.out.println("TC#07: deleteExistingWorkOrder completed");
    }

    @Test //TC#02
    public void addNewRepairWorkOrder() throws InterruptedException {
        System.out.println("TC#02: addNewRepairWorkOrder started");

        Webby.click(workOrderPage.btnNewWorkOrder);
        Thread.sleep(1000); //Wait till NewWorkOrder pop up loads

        Webby.setText(workOrderPage.inputCustomer, "Paul Walker");
        Thread.sleep(300);
        Webby.click(workOrderPage.searchResult_Customer);
        Thread.sleep(700);

        Webby.setText(workOrderPage.inputItem, "Nissan Skyline");
        Thread.sleep(300);
        Webby.click(workOrderPage.searchResult_Item);

        Thread.sleep(700);
        Webby.click(workOrderPage.btnAddAsRepairItem);
        String repairDescription = "A issue in Turbo blow off valve";
        Webby.setText(workOrderPage.inputDescription, repairDescription);
        Thread.sleep(700);
        Webby.click(workOrderPage.btnOk_Description);
        Thread.sleep(1500);
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
            System.out.println("Work Order ID not found");
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

        boolean status = customerFirstName.equals("Paul") & customerLastName.equals("Walker") & itemName.contains(repairDescription);
        Assert.assertTrue(status);

        System.out.println("TC#02: addNewRepairWorkOrder completed");
    }

    @Test //TC#03 Prerequisite: Item:"Dodge Charger" should be available in the system
    public void addNewWorkOrderWithNewCustomer() throws InterruptedException {
        System.out.println("TC#03: addNewWorkOrderWithNewCustomer started");

        Webby.click(workOrderPage.btnNewWorkOrder);
        Thread.sleep(1000); //Wait till NewWorkOrder pop up loads

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
        Thread.sleep(2000);
        Webby.click(workOrderPage.btnAddCustomerToWorkOrder);

        //Wait till NewWorkOrder pop up loads
        Thread.sleep(4000);

        Webby.setText(workOrderPage.inputItem, "Dodge Charger");
        Thread.sleep(300);
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
            System.out.println("Work Order ID not found");
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

        System.out.println("TC#03: addNewWorkOrderWithNewCustomer completed");
    }

    @Test //TC#04 Prerequisite: Customer:"Paul Walker" should be available in the system
    public void addNewWorkOrderWithNewItem() throws InterruptedException {
        System.out.println("TC#04: addNewWorkOrderWithNewItem started");

        Webby.click(workOrderPage.btnNewWorkOrder);
        Thread.sleep(1000); //Wait till NewWorkOrder pop up loads

        Webby.setText(workOrderPage.inputCustomer, "Paul Walker");
        Thread.sleep(300);
        Webby.click(workOrderPage.searchResult_Customer);
        Thread.sleep(700);

        Webby.click(workOrderPage.btnAddNewItem);
        Thread.sleep(1500);
        ranodomString = String.valueOf(genRandomNumber()); //Generate a random string
        Webby.setText(workOrderPage.inputItemName, "Toyota Supra " + ranodomString);
        Thread.sleep(700);
        Webby.click(workOrderPage.clickOnCategoryField);
        Thread.sleep(300);
        Webby.click(workOrderPage.selectOptionCar);
        Thread.sleep(300);
        Webby.click(workOrderPage.btnSave_NewItem);
        Thread.sleep(2000);
        Webby.click(workOrderPage.btnAddItemToWorkOrder);

        //Wait till NewWorkOrder pop up loads
        Thread.sleep(3000);

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
            System.out.println("Work Order ID not found");
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

        boolean status = customerFirstName.equals("Paul") & customerLastName.equals("Walker") & itemName.contains("Toyota Supra");
        Assert.assertTrue(status);

        System.out.println("TC#04: addNewWorkOrderWithNewItem completed");
    }

    @Test //TC#05
    public void verifyCustomerFieldIsMandatory() throws InterruptedException {
        System.out.println("TC#05: verifyCustomerFieldIsMandatory started");

        Webby.click(workOrderPage.btnNewWorkOrder);
        Thread.sleep(1000); //Wait till NewWorkOrder pop up loads

        Webby.setText(workOrderPage.inputItem, "Nissan Skyline");
        Thread.sleep(300);
        Webby.click(workOrderPage.searchResult_Item);

        Thread.sleep(2500);
        Webby.click(workOrderPage.btnSave_NewWorkOrder);
        Thread.sleep(1500);

        //Extract the error message
        String extractedErrorMessageTitle = Webby.getText(workOrderPage.toastMessageTitle);
        assert extractedErrorMessageTitle != null;
        String extractedErrorMessageBody = Webby.getText(workOrderPage.toastMessageBody);
        assert extractedErrorMessageBody != null;
        System.out.println("Extracted message: " + extractedErrorMessageTitle + " " + extractedErrorMessageBody);

        boolean status = extractedErrorMessageTitle.equals("Error") & extractedErrorMessageBody.equals("You must select a customer");
        Assert.assertTrue(status);

        Thread.sleep(500);
        Webby.click(workOrderPage.btnCancel_NewWorkOrder);
        Thread.sleep(3000); //Wait till the error message disappears

        System.out.println("TC#05: verifyCustomerFieldIsMandatory completed");

    }

    @Test //TC#06
    public void verifyItemFieldIsMandatory() throws InterruptedException {
        System.out.println("TC#06: verifyItemFieldIsMandatory started");

        Webby.click(workOrderPage.btnNewWorkOrder);
        Thread.sleep(1000); //Wait till NewWorkOrder pop up loads

        Webby.setText(workOrderPage.inputCustomer, "Paul Walker");
        Thread.sleep(300);
        Webby.click(workOrderPage.searchResult_Customer);

        Thread.sleep(2500);
        Webby.click(workOrderPage.btnSave_NewWorkOrder);
        Thread.sleep(1500);

        //Extract the error message
        String extractedErrorMessageTitle = Webby.getText(workOrderPage.toastMessageTitle);
        assert extractedErrorMessageTitle != null;
        String extractedErrorMessageBody = Webby.getText(workOrderPage.toastMessageBody);
        assert extractedErrorMessageBody != null;
        System.out.println("Extracted message: " + extractedErrorMessageTitle + " " + extractedErrorMessageBody);

        boolean status = extractedErrorMessageTitle.equals("Error") & extractedErrorMessageBody.equals("You must select an item");
        Assert.assertTrue(status);

        Thread.sleep(500);
        Webby.click(workOrderPage.btnCancel_NewWorkOrder);
        Thread.sleep(3000); //Wait till the error message disappears

        System.out.println("TC#06: verifyItemFieldIsMandatory completed");

    }

}
