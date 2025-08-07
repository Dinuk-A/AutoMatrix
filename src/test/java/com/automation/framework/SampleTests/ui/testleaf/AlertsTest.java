package com.automation.framework.SampleTests.ui.testleaf;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.framework.Utils.ui.BaseTest;
import com.automation.framework.utils.common.ConfigPropertyReader;
import com.automation.framework.utils.ui.BrowserUtils;
import com.automation.framework.utils.ui.ConditionUtils;
import com.automation.framework.utils.ui.ElementActions;
import com.automation.framework.utils.ui.RetrievalUtils;
import com.automation.framework.utils.ui.UiAssertionUtils;
import com.automation.framework.utils.ui.WaitUtils;
import com.automation.framework.utils.ui.PopupActions;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Epic("UI Automation")
@Feature("TestLeaf Module")
@Story("Alerts Validation")
public class AlertsTest extends BaseTest {

    ElementActions actions;
    PopupActions popupActions;

    // open this web page once per each test method ✅
    @BeforeMethod
    public void navigateToWebPage() {
        actions = new ElementActions(driver);
        popupActions = new PopupActions(driver);
        String baseURl = ConfigPropertyReader.getProperty("testleaf.base.url");
        String actionsPageUrl = ConfigPropertyReader.getProperty("testleaf.alerts.endpoint");
        String fullUrl = baseURl + actionsPageUrl;
        BrowserUtils.openUrl(driver, fullUrl);
    }

    @Test(enabled = true, priority = 1, description = "Validate that the user can interact with alerts")
    @Severity(SeverityLevel.NORMAL)
    @Description("Open the TestLeaf actions page, Perform alert actions.")
    public void jsAlertsTest() throws InterruptedException {

        // validate page title
        String expectedTitle = "Alert";
        UiAssertionUtils.assertPageTitle(driver, expectedTitle);

        // #1 ✅
        // click on the first alert button and validate the text
        By alertButton1Locator = By.id("j_idt88:j_idt91");
        WebElement alertButton = WaitUtils.waitForElementClickable(driver, alertButton1Locator);
        actions.click(alertButton);

        // wait for the alert to be present and switch to it
        Alert firstAlert = WaitUtils.waitForBrowserPopup(driver);
        
        // validate alert text
        String alertText = popupActions.getPopupText(firstAlert);
        System.out.println("Alert Text: " + alertText);

        // accept the alert popup
        popupActions.acceptPopup(firstAlert);

        // validate UI text
        By alertSuccessMsgLocator = By.id("simple_result");
        WebElement alertSuccessMsgElement = WaitUtils.waitForElementVisible(driver, alertSuccessMsgLocator);
        UiAssertionUtils.assertElementTextEquals(alertSuccessMsgElement, "You have successfully clicked an alert");

        Thread.sleep(2000); // wait for 2 seconds to observe the alert dismissal

    }
}
