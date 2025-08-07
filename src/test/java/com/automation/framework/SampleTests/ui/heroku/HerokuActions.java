package com.automation.framework.SampleTests.ui.heroku;

import com.automation.framework.Utils.ui.BaseTest;
import com.automation.framework.utils.common.ConfigPropertyReader;
import com.automation.framework.utils.common.JsonReader;
import com.automation.framework.utils.ui.BrowserUtils;
import com.automation.framework.utils.ui.ConditionUtils;
import com.automation.framework.utils.ui.ElementActions;
import com.automation.framework.utils.ui.PopupActions;
import com.automation.framework.utils.ui.RetrievalUtils;
import com.automation.framework.utils.ui.UiAssertionUtils;
import com.automation.framework.utils.ui.WaitUtils;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HerokuActions extends BaseTest {

    ElementActions actions;
    PopupActions popupActions;

    @BeforeMethod
    public void navigateToHomePage() {

        actions = new ElementActions(driver);
        popupActions = new PopupActions(driver);
    }

    @Test(enabled = true, priority = 1, description = "Validate that the user do multiple actions on Heroku app")
    public void herokuActionsTest() throws InterruptedException {

        // read the base URL from config file
        String herokuBaseUrl = ConfigPropertyReader.getProperty("heroku.base.url");
        BrowserUtils.openUrl(driver, herokuBaseUrl);

        // Find Form Authentication link and click on it
        By formAuthenticationLinkLocator = By.linkText("Form Authentication");
        WebElement formAuthenticationLink = WaitUtils.waitForElementVisible(driver, formAuthenticationLinkLocator);
        actions.clickLink(formAuthenticationLink);

        // read user credentials from JSON file
        String userFilePath = "D:\\ST-SQA\\test-automation-framework\\src\\test\\resources\\data\\ui\\user.json";
        String usernameValue = (String) JsonReader.fetchJsonValueByKey(userFilePath, "username");
        String passwordValue = (String) JsonReader.fetchJsonValueByKey(userFilePath, "password");

        By inputLocatorUN = By.id("username");
        WebElement usernameInput = WaitUtils.waitForElementVisible(driver, inputLocatorUN);
        actions.clearExistingTextValue(usernameInput);
        actions.type(usernameInput, usernameValue);

        By inputLocatorPW = By.id("password");
        WebElement passwordInput = WaitUtils.waitForElementVisible(driver, inputLocatorPW);
        actions.clearExistingTextValue(passwordInput);
        actions.typeAndPressEnter(passwordInput, passwordValue);

        // or we can locate the login button and click on it too ✅
        // By loginButtonLocator = By.xpath("//button[@type='submit']");
        // WebElement loginButton = WaitUtils.waitForElementVisible(driver,
        // loginButtonLocator);
        // actions.jsClick(loginButton);

        // click OK on popup
        //not working 💥💥💥
        //popupActions.acceptPopup();

        // Assert that the login was successful by checking the welcome message
        By welcomeMessageLocator = By.xpath("//*[@id='content']/div/h2");
        WebElement welcomeMsgElement = WaitUtils.waitForElementVisible(driver, welcomeMessageLocator);

        // get the text of the welcome message
        String welcomeMsgText = RetrievalUtils.getElementText(welcomeMsgElement);
        System.out.println(welcomeMsgText);

        UiAssertionUtils.assertElementTextEquals(welcomeMsgElement, "Secure Area");

        // go back to the previous page
        BrowserUtils.goBack(driver);
        System.out.println(BrowserUtils.getCurrentUrl(driver));

        // go back for main menu
        BrowserUtils.goBack(driver);
        System.out.println(BrowserUtils.getCurrentUrl(driver));

        // Find Checkboxes link and click on it
        By checkboxesLinkLocator = By.linkText("Checkboxes");
        WebElement checkboxesLink = WaitUtils.waitForElementVisible(driver, checkboxesLinkLocator);
        actions.clickLink(checkboxesLink);

        System.out.println(BrowserUtils.getCurrentUrl(driver));

        // locate checkbox 1 and click on it
        By checkbox1Locator = By.xpath("//form[@id='checkboxes']/input[1]");
        WebElement checkbox1 = WaitUtils.waitForElementVisible(driver, checkbox1Locator);
        actions.jsClick(checkbox1);

        // locate already checked checkbox 2 and click on it to uncheck it
        By checkbox2Locator = By.xpath("//form[@id='checkboxes']/input[2]");
        WebElement checkbox2 = WaitUtils.waitForElementVisible(driver, checkbox2Locator);

        if (ConditionUtils.isElementSelected(checkbox2)) {
            actions.jsClick(checkbox2);
        } else {
            System.out.println("Checkbox 2 is already unchecked.");
        }

        // go to google
        String googleHomePageUrl = ConfigPropertyReader.getProperty("google.base.url");
        BrowserUtils.openUrl(driver, googleHomePageUrl);

        // find google text box and type heroku app
        By googleInputLocator = By.name("q");
        WebElement googleInput = WaitUtils.waitForElementVisible(driver, googleInputLocator);
        actions.clearExistingTextValue(googleInput);
        actions.typeAndPressEnter(googleInput, "Selenium Automation");

        Path path = Paths.get("src/test/resources/practice-htmls/local_page.html");
        String fileUrl = path.toAbsolutePath().toUri().toString();
        BrowserUtils.openUrl(driver, fileUrl);

    }
}
