package com.automation.framework.SampleTests.ui.testleaf;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Epic("UI Automation")
@Feature("TestLeaf Module")
@Story("HyperLink Validation")
public class HyperLinkTest extends BaseTest {

    ElementActions actions;

    // open this web page once per each test method ✅
    @BeforeMethod
    public void navigateToWebPage() {
        actions = new ElementActions(driver);
        String baseURl = ConfigPropertyReader.getProperty("testleaf.base.url");
        String hyperLinksUrl = ConfigPropertyReader.getProperty("testleaf.hyperlink.endpoint");
        String fullUrl = baseURl + hyperLinksUrl;
        BrowserUtils.openUrl(driver, fullUrl);
    }

    @Test(enabled = true, priority = 1, description = "Validate that the user can interact with hyperlinks")
    @Severity(SeverityLevel.NORMAL)
    @Description("Open the TestLeaf hyperlink page, Perform hyperlink actions.")
    public void hyperLinksTest() {

        // validate page title
        String expectedTitle = "Link Components";
        UiAssertionUtils.assertPageTitle(driver, expectedTitle);

        // #1 ✅
        // wait for element to be clickable and click on the first hyperlink
        By dashboardLinkLocator = By.linkText("Go to Dashboard");
        WebElement dashboardLink = WaitUtils.waitForElementClickable(driver, dashboardLinkLocator);
        actions.clickLink(dashboardLink);

        // verify the current page title with expected text
        String currentPageTitle = BrowserUtils.getPageTitle(driver);
        System.out.println("Current Page Title: " + currentPageTitle);
        UiAssertionUtils.assertPageTitle(driver, currentPageTitle);

        // go back to the hyperlink test page
        BrowserUtils.goBack(driver);

        // #2 ✅
        // find the URL address without clicking
        // use partial link text to locate the hyperlink
        By secondUrlLocator = By.partialLinkText("Find the URL without clicki");
        WebElement secondUrlLink = WaitUtils.waitForElementVisible(driver, secondUrlLocator);
        String secondUrl = RetrievalUtils.getAttribute(secondUrlLink, "href");
        System.out.println("Second URL: " + secondUrl);

        // #3 ✅
        // find if a link is broken
        By brokenLinkLocator = By.linkText("Broken?");
        WebElement brokenLink = WaitUtils.waitForElementClickable(driver, brokenLinkLocator);
        actions.clickLink(brokenLink);

        // verify the current page title with expected partial text
        String brokenLinkTitle = BrowserUtils.getPageTitle(driver);
        System.out.println("Broken Link Page Title: " + brokenLinkTitle);
        if (brokenLinkTitle.contains("404")) {
            System.out.println("The link is broken.");
        } else {
            System.out.println("The link is not broken.");

        }

        // go back to the hyperlink test page
        BrowserUtils.goBack(driver);

        // #4 ✅
        // find duplications(same link text as #1)
        // actions.clickLink(dashboardLink); ❌
        // this will give staleElementreferenceexception === because we navigated back
        // from the previous page, dashboardLink reference is lost now.cant access with
        // that name again,,,this happens if page refreshes too

        // so we need to capture that element again
        By duplicatedDashboardLinkLocator = By.linkText("Go to Dashboard");
        WebElement duplicatedDashboardLink = WaitUtils.waitForElementClickable(driver, duplicatedDashboardLinkLocator);
        actions.clickLink(duplicatedDashboardLink);

         // go back to the hyperlink test page
         BrowserUtils.goBack(driver);

        // #5 ✅
        // find how many link elements on this page
        int linkCountDirectly = driver.findElements(By.tagName("a")).size();
        System.out.println("Total number of links on the page: " + linkCountDirectly);

        // #6 ✅
        // find how many links on just the main layout
        By mainLayoutLocator = By.id("j_idt87");
        WebElement mainLayout = WaitUtils.waitForElementVisible(driver, mainLayoutLocator);
        int linkCountInLayout = mainLayout.findElements(By.tagName("a")).size();
        System.out.println("Total number of links in the main layout: " + linkCountInLayout);


    }

}
