package com.automation.framework.utils.ui;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

 // This class is a placeholder for element actions.
public class ElementActions {   

    private WebDriver driver;

    // Constructor to initialize the WebDriver
    public ElementActions(WebDriver driver) {
        this.driver = driver;
    }

    // Click on an element, using Actions as fallback if normal click fails
    public void click(WebElement element) {
        try {
            element.click();
        } catch (ElementNotInteractableException e) {
            Actions actions = new Actions(driver);
            actions.moveToElement(element)
                    .click()
                    .perform(); 
        }
    }

    //Javascript click on an element
    public void jsClick(WebElement element) {
        String script = "arguments[0].click();";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    // Double click on an element
    public void doubleClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }

    // Right click on an element
    public void rightClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
    }

   // Type text into an input field
    public void type(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    // Clear text from an input field
    public void clear(WebElement element) {
        element.clear();
    }

    // Select from dropdown using visible text
    public void selectByVisibleText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }












    // Hover over an element
    public void hover(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
    
}
