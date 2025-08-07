package com.automation.framework.SampleTests.ui.testleaf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
//import org.openqa.selenium.Point;

import com.automation.framework.Utils.ui.BaseTest;
import com.automation.framework.utils.common.ConfigPropertyReader;
import com.automation.framework.utils.ui.BrowserUtils;
import com.automation.framework.utils.ui.ElementActions;
import com.automation.framework.utils.ui.RetrievalUtils;
import com.automation.framework.utils.ui.UiAssertionUtils;
import com.automation.framework.utils.ui.WaitUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import org.testng.annotations.Test;

@Epic("UI Automation")
@Feature("TestLeaf Module")
@Story("Button Validation")
public class ButtonsTest extends BaseTest {

    // Create an instance of ElementActions
    ElementActions actions;

    @BeforeMethod
    public void navigateToWebPage() {
        actions = new ElementActions(driver);
        String baseURl = ConfigPropertyReader.getProperty("testleaf.base.url");
        String textInputUrl = ConfigPropertyReader.getProperty("testleaf.button.endpoint");
        String fullUrl = baseURl + textInputUrl;
        BrowserUtils.openUrl(driver, fullUrl);
    }

    @Test(enabled = true, priority = 1, description = "Validate that the user can interact with buttons")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Open the TestLeaf text input page, Perform various button actions.")
    public void allButtonsTest() {

        // click the first button and verify the redirected page's title ✅
        // check element existence in the DOM and wait for it to be present
        By btnOneLocator = By.id("j_idt88:j_idt90");
        WebElement button1 = WaitUtils.waitForElementVisible(driver, btnOneLocator);
        actions.click(button1);

        // get current page's title (just to print on console)
        String currentPageTitle = BrowserUtils.getPageTitle(driver);
        System.out.println(currentPageTitle);

        // verify the current page title with expected text
        String expectedTitle = "Dashboard";
        UiAssertionUtils.assertPageTitle(driver, expectedTitle);

        // go back to the button test page
        BrowserUtils.goBack(driver);

        // Confirm if the button is disabled. ✅
        By btnTwoLocator = By.id("j_idt88:j_idt92");
        WebElement button2 = driver.findElement(btnTwoLocator);
        UiAssertionUtils.assertElementDisabled(button2);

        // find the position of a button ✅
        By positionBtnLocator = By.id("j_idt88:j_idt94");
        WebElement positionButton = driver.findElement(positionBtnLocator);
        System.out.println(RetrievalUtils.getLocation(positionButton));

    }
}
