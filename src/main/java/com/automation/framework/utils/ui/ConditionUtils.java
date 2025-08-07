package com.automation.framework.utils.ui;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConditionUtils {

     // Check if the element is displayed (visible on screen)
    public static boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Check if the element is enabled (interactable)
    public static boolean isElementEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Check if the element is selected (for checkboxes, radios, etc.)
    public static boolean isElementSelected(WebElement element) {
        try {
            return element.isSelected();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Check if an element exists in the DOM
    public static boolean doesElementExist(By locator, WebDriver driver) {
        List<WebElement> elements = driver.findElements(locator);
        return !elements.isEmpty();
    }

    // Check if specific text is present in an element
    public static boolean isTextPresentInElement(WebElement element, String expectedText) {
        try {
            return element.getText().contains(expectedText);
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
