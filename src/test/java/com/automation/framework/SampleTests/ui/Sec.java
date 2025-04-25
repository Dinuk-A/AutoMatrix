package com.automation.framework.SampleTests.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Sec {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void openGoogleAndTypeHello() throws InterruptedException {
        driver.get("https://www.google.com");

        // Print the page title
        System.out.println("Page Title: " + driver.getTitle());

        // Type "hello" into the search box
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("hello");

        // Wait for 3 seconds so you can actually see the typed text
        Thread.sleep(3000);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
