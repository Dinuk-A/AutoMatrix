package com.automation.framework.utils.ui;

import org.openqa.selenium.WebElement;

public class RetrievalUtils {

    // Get the visible text from an element
    public static String getElementText(WebElement element) {
        return element.getText();
    }

    // Get the value of a specific attribute of an element
    public static String getAttribute(WebElement element, String attributeName) {
        return element.getDomProperty(attributeName);
    }

    // get the typed value in a text box
    public static String getInputValue(WebElement element) {
        return element.getDomProperty("value");
    }

    // Get the value of a specific CSS property
    public static String getCssValue(WebElement element, String cssProperty) {
        return element.getCssValue(cssProperty);
    }

    // Get the tag name of the element (e.g., "input", "div")
    public static String getTagName(WebElement element) {
        return element.getTagName();
    }

    // Get the location (x, y coordinates) of the element on the page
    public static String getLocation(WebElement element) {
        return element.getLocation().toString();
    }

    // Get the dimensions (height and width) of the element
    public static String getSize(WebElement element) {
        return element.getSize().toString();
    }

    // Get the value of a link text from an element
    public String getLinkText(WebElement element) {
        return element.getText();
    }

    // Get the value of a link href attribute from an element
    public String getLinkHref(WebElement element) {
        return element.getDomAttribute("href");
    }
    
    
}
