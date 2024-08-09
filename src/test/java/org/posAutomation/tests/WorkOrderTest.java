package org.posAutomation.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.posAutomation.functions.Webby;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkOrderTest extends LoginTest {
    public String extractedText;
    public String extractedWorkOrderId;

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

    public void searchWorkOrderById(String searchKey) throws InterruptedException {
        //Search the above created Work Order
        Webby.setText(workOrderPage.inputSearch, searchKey);
        Webby.click(workOrderPage.btnSearch);
        Thread.sleep(3000);
    }


    @Test
    public void addNewWorkOrder_TC01() throws InterruptedException {
        Webby.click(workOrderPage.iconWorkOrder);
        Webby.click(workOrderPage.btnNewWorkOrder);
        Webby.setText(workOrderPage.inputCustomer, "Paul Walker");
        Thread.sleep(1000);
        Webby.click(workOrderPage.searchResult_Customer);
        Webby.setText(workOrderPage.inputItem, "Nissan Skyline");
        Thread.sleep(1000);
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
        String customerLastName = Webby.getText(workOrderPage.firstSearchResult_customerLastName);
        String itemName = Webby.getText(workOrderPage.firstSearchResult_itemName);
        System.out.println("Found search result: " + customerFirstName + " " + customerLastName + "'s " + itemName);

        assert customerFirstName != null;
        assert customerLastName != null;
        assert itemName != null;
        Boolean status = customerFirstName.equals("Paul") & customerLastName.equals("Walker") & itemName.equals("Nissan Skyline");
        System.out.println("Order verification passed? " + status);

        //I need to pass the test if the "status" true and fail the test if the "status" is false
        //I DON'T KNOW HOW TO DO :(
    }


    @Test(dependsOnMethods = {"addNewWorkOrder_TC01"})
    public void deleteWorkOrder_TC07() throws InterruptedException {
        //extractedWorkOrderId = "90"; //hardcoded for testing purpose
        Webby.click(workOrderPage.iconWorkOrder);
        searchWorkOrderById(extractedWorkOrderId);

        //Extract the search result
        String customerFirstName2 = Webby.getText(workOrderPage.firstSearchResult_customerFirstName);
        String customerLastName2 = Webby.getText(workOrderPage.firstSearchResult_customerLastName);
        String itemName2 = Webby.getText(workOrderPage.firstSearchResult_itemName);
        System.out.println("Found search result: " + customerFirstName2 + " " + customerLastName2 + "'s " + itemName2);

        assert customerFirstName2 != null;
        assert customerLastName2 != null;
        assert itemName2 != null;
        Boolean statusBeforeDeletion = customerFirstName2.equals("Paul") & customerLastName2.equals("Walker") & itemName2.equals("Nissan Skyline");
        System.out.println("Found the searched Work Order? " + statusBeforeDeletion);

        if (statusBeforeDeletion){
            Webby.click(workOrderPage.selectTheFirstSearchResult);
            Thread.sleep(500); //wait till Delete button appears
            Webby.click(workOrderPage.btnDelete);
            Thread.sleep(1000); //wait till delete confirmation prompt appears
            Webby.click(workOrderPage.btnOK_DeleteConfirmation);
            Thread.sleep(2000); //wait till page refresh
            System.out.println("Work Order " + extractedWorkOrderId + " deleted");

            //Confirm the deletion
            searchWorkOrderById(extractedWorkOrderId);

            String searchResult = Webby.getText(workOrderPage.noSearchResultWarning);
            System.out.println("Search results says: " + searchResult);
            assert searchResult != null;
            Boolean statusAfterDeletion = searchResult.equals("No Work Orders");
            System.out.println("Was it deleted? " + statusAfterDeletion);

            //I need to pass the test if the search result is "No Work Orders" and fail the work order is still available
            //I DON'T KNOW HOW TO DO :(
        }
    }


}
