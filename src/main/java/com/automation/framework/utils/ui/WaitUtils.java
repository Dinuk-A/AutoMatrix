package com.automation.framework.utils.ui;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class WaitUtils {

    // FOR VISIBILITY
    // Custom timeout version
    public WebElement waitForElementVisible(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Default timeout version (uses 10 seconds)
    public WebElement waitForElementVisible(WebDriver driver, By locator) {
        return waitForElementVisible(driver, locator, 10);
    }

    // FOR CLICKABLE
    // Custom timeout version
    public WebElement waitForElementClickable(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Default timeout version (uses 10 seconds)
    public WebElement waitForElementClickable(WebDriver driver, By locator) {
        return waitForElementClickable(driver, locator, 10);
    }

    // Presence in DOM(not necessarily visible)
    // Custom timeout version
    public WebElement waitForElementPresence(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Default timeout version (uses 10 seconds)
    public WebElement waitForElementPresence(WebDriver driver, By locator) {
        return waitForElementPresence(driver, locator, 10);
    }

    // Text to be present in element
    // Custom timeout version
    public boolean waitForTextInElement(WebDriver driver, By locator, String text, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    // Default timeout version (uses 10 seconds)
    public boolean waitForTextInElement(WebDriver driver, By locator, String text) {
        return waitForTextInElement(driver, locator, text, 10);
    }

    // Alert is present
    // Custom timeout version
    public Alert waitForAlert(WebDriver driver, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    // Default timeout version (uses 10 seconds)
    public Alert waitForAlert(WebDriver driver) {
        return waitForAlert(driver, 10);
    }

}
