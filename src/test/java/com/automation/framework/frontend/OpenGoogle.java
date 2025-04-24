package com.automation.framework.frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenGoogle {

    WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setUp(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            // options.addArguments("--headless");
            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);
        }

         // Uncomment if running headless
            // options.addArguments("--headless");

        driver.manage().window().maximize();

    }

    @Test
    public void openGoogle() {
        driver.get("https://www.google.com");
        System.out.println("Title of the page is: " + driver.getTitle());

        // find the search input textbox
        WebElement searchBox = driver.findElement(By.name("q"));
        // enter the search term
        searchBox.sendKeys("Hello World");
        // submit the search
        searchBox.submit();

        // Wait for results to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //String title = driver.getTitle();
        //Assert.assertTrue(title.contains("Selenium WebDriver"));

    }

    // quit the browser after test
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }
}
