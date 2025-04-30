package com.automation.framework.Core.ui;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.chromium.ChromiumDriver;
//import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    @Parameters({ "browser", "headless" })
    public void setUp(String browser,String headless) {

        boolean isHeadless = headless.equalsIgnoreCase("true");

        switch (browser.toLowerCase()) {
            case "chrome":
                driver = initializeChromeDriver(isHeadless);
                break;
            case "firefox":
                driver = initializeFirefoxDriver(isHeadless);
                break;
            case "edge":
                driver = initializeEdgeDriver(isHeadless);
                break;
            //case "chromium":
            //    driver = initializeChromiumDriver(isHeadless);
            //    break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);

        }

        // Set global implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Create an explicit wait object for dynamic waits
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private WebDriver initializeChromeDriver(boolean isHeadless) {
        ChromeOptions options = new ChromeOptions();
        if (isHeadless) {
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
        }
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        return new ChromeDriver(options);
    }

    private WebDriver initializeEdgeDriver(boolean isHeadless) {
        EdgeOptions options = new EdgeOptions();
        if (isHeadless) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
        }
        options.addArguments("--start-maximized");
        return new EdgeDriver(options);
    }

    private WebDriver initializeFirefoxDriver(boolean isHeadless) {
        FirefoxOptions options = new FirefoxOptions();
        if (isHeadless) {
            options.addArguments("-headless");
        }
        options.addArguments("--start-maximized");
        return new FirefoxDriver(options);
    }


    //private WebDriver initializeChromiumDriver(boolean isHeadless) {
    //    ChromiumOptions options = new ChromiumOptions();
    //    if (isHeadless) {
    //        options.addArguments("--headless");
    //        options.addArguments("--disable-gpu");
    //        options.addArguments("--no-sandbox");
    //    }
    //    options.addArguments("--start-maximized");
    //    return new ChromiumDriver(options);
    //}

    //open the URL
    public void openBaseUrl(String url) {
        driver.get(url);
    }

    // Close the browser and quit the WebDriver session
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
