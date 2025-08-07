package com.automation.framework.utils.ui;

import org.openqa.selenium.WebDriver;

public class BrowserUtils {

    public static void openUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public static void goBack(WebDriver driver) {
        driver.navigate().back();
    }

    public static void goForward(WebDriver driver) {
        driver.navigate().forward();
    }

    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public static String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public static String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

}
