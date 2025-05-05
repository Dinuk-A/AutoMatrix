package com.automation.framework.utils.ui;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class UiAssertionUtils {
    /*
     * Asserts that an element is displayed (visible in the DOM).
     */
    public static void assertElementVisible(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "Element is not visible on the page.");
    }

    /*
     * Asserts that an element is enabled (interactable).
     */
    public static void assertElementEnabled(WebElement element) {
        Assert.assertTrue(element.isEnabled(), "Element is not enabled.");
    }

    /*
     * Asserts that an element contains the expected text.
     */
    public static void assertElementTextEquals(WebElement element, String expectedText) {
        String actualText = element.getText().trim();
        Assert.assertEquals(actualText, expectedText, "Element text does not match.");
    }

    /*
     * Asserts that an element contains a partial text.
     */
    public static void assertElementContainsText(WebElement element, String partialText) {
        String actualText = element.getText();
        Assert.assertTrue(actualText.contains(partialText), "Element does not contain expected partial text.");
    }

    /*
     * Asserts that an element is not displayed (e.g., hidden or not present).
     */
    public static void assertElementNotVisible(WebElement element) {
        Assert.assertFalse(element.isDisplayed(), "Element is visible, but expected to be hidden.");
    }

    /*
     * Asserts that the actual page title matches the expected title.
     */
    public static void assertPageTitle(String actualTitle, String expectedTitle) {
        Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match.");
    }

    /*
     * Asserts that two strings (e.g., from fields) match exactly.
     */
    public static void assertTextEquals(String actual, String expected) {
        Assert.assertEquals(actual, expected, "Text values do not match.");
    }
}
