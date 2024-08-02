# POS Automation Framework

This is a simple Selenium automation framework built to test a POS using Java, Maven, TestNG, Allure, and PageFactory. This framework is designed to be easy to set up and extend for web application testing.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)

## Prerequisites

- Java JDK (8 or above)
- Maven
- ChromeDriver (or another WebDriver of your choice)
- IDE (IntelliJ IDEA, Eclipse, etc.)

## Project Structure

```plaintext
selenium-automation-framework
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   └── java
│   │       └── org
│   │           └── ucsc
│   │               └── framework
│   │                   └── pages
│   │                       └── _01_LoginPage.java
│   │                       └── _02_DashboardPage.java
│   │                       └── _03_CustomerPage.java
│   │                       └── _04_InventoryPage.java
│   │                       └── _05_WorkOrderPage.java
│   │                       └── BasePage.java
│   └── test
│       └── java
│           └── org
│               └── ucsc
│                   └── tests
│                       └── LoginTest.java
```

## Setup Instructions

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/akash-devanarayana/pos-automation.git
    ```

2. **Update WebDriver Path:**

    In `BasePage.java`, update the path to your WebDriver executable (e.g., `chromedriver`).

    ```java
    System.setProperty("webdriver.chrome.driver", "enter path to web driver here");
    ```

3. **Add Dependencies:**

    Make sure your `pom.xml` file contains the necessary dependencies:

    ```xml
    <dependencies>
        <!-- Selenium Java -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.0.0</version>
        </dependency>
        <!-- TestNG -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.4.0</version>
            <scope>test</scope>
        </dependency>
        <!-- Allure TestNG -->
        <dependency>
            <groupId>io.qameta.allure</groupId>
            <artifactId>allure-testng</artifactId>
            <version>2.13.8</version>
        </dependency>
    </dependencies>
    ```
