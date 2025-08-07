package com.automation.framework.SampleTests.ui.testleaf;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("UI Automation")
@Feature("TestLeaf Module")
@Story("Text Input Field Validation")
public class TextInputTest extends BaseTest {

    // Create an instance of ElementActions
    ElementActions actions = new ElementActions(driver);

    // open this web page once per each test method ✅
    @BeforeMethod
    public void navigateToWebPage() {
        String baseURl = ConfigPropertyReader.getProperty("testleaf.base.url");
        String textInputUrl = ConfigPropertyReader.getProperty("testleaf.textinput.endpoint");
        String fullUrl = baseURl + textInputUrl;
        BrowserUtils.openUrl(driver, fullUrl);
    }

    // ✅
    @Test(enabled = false, priority = 1, description = "Validate that the user can type text into a TestLeaf input field")
    @Severity(SeverityLevel.NORMAL)
    @Description("Open the TestLeaf text input page, Perform text input actions.")
    public void normalTextInput() {

        // validate page title
        String expectedTitle = "Input Components";
        UiAssertionUtils.assertPageTitle(driver, expectedTitle);

        // check element existence in the DOM and wait for it to be present
        By textInputLocator = By.id("j_idt88:name");
        WebElement textInputElement = WaitUtils.waitForElementVisible(driver, textInputLocator);

        // clear existing text value
        actions.clearExistingTextValue(textInputElement);

        // type text into the input field
        String textToType = "Dinuka Pramod";
        actions.type(textInputElement, textToType);

        // to validate in console
        System.out.println("normalTextInput successfully executed");

    }

    // ✅
    @Test(enabled = false, priority = 2, description = "Validate that the user can type text into a TestLeaf input field where a value is alreeady exists")
    @Severity(SeverityLevel.NORMAL)
    @Description("Open the TestLeaf text input page, Perform text append actions.")
    public void appendTextTest() {

        // validate page title
        String expectedTitle = "Input Components";
        UiAssertionUtils.assertPageTitle(driver, expectedTitle);

        // check element existence in the DOM and wait for it to be present
        By textInputLocator = By.id("j_idt88:j_idt91");
        WebElement textInputElement = WaitUtils.waitForElementVisible(driver, textInputLocator);

        // type text into the input field
        String textToType = " India";
        actions.type(textInputElement, textToType);

        // to validate in console
        System.out.println("appendTextTest successfully executed");

    }

    // ✅
    @Test(priority = 3, description = "Validate multiple text input actions on the TestLeaf input fields")
    @Severity(SeverityLevel.NORMAL)
    @Description("Performs various text input actions such as typing, clearing, appending, and checking disabled input fields on the TestLeaf page.")
    public void moreTextInputTestsInOne() {

        // Verify if text box is disabled ✅
        // approach 1 ✅
        // just show result in console, wont pass of fail tests
        By inputLocator = By.id("j_idt88:j_idt93");
        WebElement textInput1 = driver.findElement(inputLocator);
        Boolean isInputEnabled = ConditionUtils.isElementEnabled(textInput1);
        System.out.println(isInputEnabled ? "Element is enabled" : "Element is disabled");

        // approach 2 ✅
        // pass or fail tests based on elements availability
        UiAssertionUtils.assertElementDisabled(textInput1);

        // retrieve the typed text ✅
        // first check element existence in the DOM and wait for it to be present
        By textInputLocator = By.id("j_idt88:j_idt97");
        WebElement textInputElement = WaitUtils.waitForElementVisible(driver, textInputLocator);
        // then get value
        String value = RetrievalUtils.getInputValue(textInputElement);
        System.out.println(value);

        // move from one input to next ✅
        By textInputLocatorOne = By.id("j_idt88:j_idt99");
        WebElement textInputElementOne = WaitUtils.waitForElementVisible(driver, textInputLocatorOne);
        String txtValueToTypedInNextInput = "these will be typed in next input";
        actions.type(textInputElementOne, "Random text" + Keys.TAB + txtValueToTypedInNextInput);
        // validate that text is typed in the next input
        WebElement nextElement = driver.findElement(By.id("j_idt88:j_idt101"));
        String valueInNextInput = RetrievalUtils.getInputValue(nextElement);
        System.out.println(valueInNextInput);
        UiAssertionUtils.assertTextEquals(valueInNextInput, txtValueToTypedInNextInput);

        // to validate in console
        System.out.println("moreTextInputTestsInOne successfully executed");

    }

}
