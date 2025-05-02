package com.automation.framework.utils.ui;

import org.openqa.selenium.WebElement;

public class RetrievalUtils {
     // Get the visible text from an element
    public String getElementText(WebElement element) {
        return element.getText();
    }

    // Get the value of a specific attribute of an element
    public String getAttribute(WebElement element, String attributeName) {
        return element.getDomProperty(attributeName);
    }

    // Get the value of a specific CSS property
    public String getCssValue(WebElement element, String cssProperty) {
        return element.getCssValue(cssProperty);
    }

    // Get the tag name of the element (e.g., "input", "div")
    public String getTagName(WebElement element) {
        return element.getTagName();
    }

    // Get the location (x, y coordinates) of the element on the page
    public String getLocation(WebElement element) {
        return element.getLocation().toString();
    }

    // Get the dimensions (height and width) of the element
    public String getSize(WebElement element) {
        return element.getSize().toString();
    }
}
