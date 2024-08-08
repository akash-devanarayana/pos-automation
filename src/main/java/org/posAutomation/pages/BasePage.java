package org.posAutomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import static org.posAutomation.utils.Constants.LOGIN_PAGE_URL;

public class BasePage {
    public static WebDriver driver;

    protected _01_LoginPage loginPage;
    protected _02_DashboardPage dashboardPage;
    protected _03_CustomerPage customerPage;
    protected _04_InventoryPage inventoryPage;
    protected _05_WorkOrderPage workOrderPage;

    public BasePage() {
        initDriver();
        initPages();
    }

    private void initDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            driver.get(LOGIN_PAGE_URL);
        }
    }

    private void initPages() {
        loginPage = new _01_LoginPage();
        dashboardPage = new _02_DashboardPage();
        customerPage = new _03_CustomerPage();
        inventoryPage = new _04_InventoryPage();
        workOrderPage = new _05_WorkOrderPage();

        PageFactory.initElements(driver, loginPage);
        PageFactory.initElements(driver, dashboardPage);
        PageFactory.initElements(driver, customerPage);
        PageFactory.initElements(driver, inventoryPage);
        PageFactory.initElements(driver, workOrderPage);
    }
}
