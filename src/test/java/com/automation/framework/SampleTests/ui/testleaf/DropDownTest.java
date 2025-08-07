package com.automation.framework.SampleTests.ui.testleaf;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.framework.Utils.ui.BaseTest;
import com.automation.framework.utils.ui.BrowserUtils;
import com.automation.framework.utils.ui.ElementActions;
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
@Story("Dropdown Validation")
public class DropDownTest extends BaseTest {

    ElementActions actions = new ElementActions(driver);

    @BeforeMethod
    public void navigateToWebPage() {

        Path path = Paths.get("src/test/resources/practice-htmls/dropdowns_test.html");
        String fileUrl = path.toAbsolutePath().toUri().toString();
        BrowserUtils.openUrl(driver, fileUrl);

        // or use
        // String filePath =
        // "D:\\ST-SQA\\test-automation-framework\\src\\test\\resources\\practice-htmls\\dropdowns_test.html";
        // String fileUrl = "file:///" + filePath.replace("\\", "/");
    }

    @Test(enabled = true, priority = 1, description = "Validate that the user can interact with dropdowns")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Open the sample dropdown HTML page, Perform various dropdown actions.")
    public void dropDownsTest() throws InterruptedException {

        // to confirm the page is loaded and ready
        String pageTitle = BrowserUtils.getPageTitle(driver);
        System.out.println("Page Title: " + pageTitle);

        // #1 ✅
        // locate first select element and select a value by visible text
        By fruitsSelectLocator = By.id("fruitSelect");

        // wait for the element to be visible
        WebElement fruitsSelectElement = WaitUtils.waitForElementVisible(driver, fruitsSelectLocator);

        // select a value by visible text
        actions.selectByVisibleText(fruitsSelectElement, "Banana");

        // #2 ✅
        // select element by value
        WebElement coloursSelectElement = WaitUtils.waitForElementVisible(driver, By.id("colorSelect"));
        actions.selectByValue(coloursSelectElement, "green");

        // #3 ✅
        // select element by index
        WebElement countriesSelectElement = WaitUtils.waitForElementVisible(driver, By.id("countrySelect"));
        actions.selectByIndex(countriesSelectElement, 3);

        Thread.sleep(3000);

    }

}
