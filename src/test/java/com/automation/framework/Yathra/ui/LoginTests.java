package com.automation.framework.Yathra.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.framework.Utils.ui.BaseTest;
import com.automation.framework.utils.common.JsonReader;
import com.automation.framework.utils.ui.BrowserUtils;
import com.automation.framework.utils.ui.ElementActions;
import com.automation.framework.utils.ui.UiAssertionUtils;
import com.automation.framework.utils.ui.WaitUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginTests extends BaseTest {

    ElementActions actions;

    @BeforeMethod
    public void navigateToLoginPage() {
        actions = new ElementActions(driver);
        BrowserUtils.openUrl(driver, "http://localhost:8081/login");
    }

    // common method for all 4 login scenarios
    public void testLoginByGivenCredentials(String username, String password) {

        // read credentials file
        String credentialsFilePath = "src/test/resources/data/ui/yathralogin.json";
        Object unObj = JsonReader.fetchJsonValueByKey(credentialsFilePath, username);
        Object pwObj = JsonReader.fetchJsonValueByKey(credentialsFilePath, password);

        // convert to String
        String un = (String) unObj;
        String pw = (String) pwObj;

        // username input
        WebElement inputUN = WaitUtils.waitForElementVisible(driver, By.id("loginUN"));
        actions.type(inputUN, un);

        // password input
        WebElement inputPW = WaitUtils.waitForElementVisible(driver, By.id("loginPW"));
        actions.type(inputPW, pw);

        // login btn
        WebElement loginBtn = WaitUtils.waitForElementClickable(driver, By.id("loginBtn"));
        actions.jsClick(loginBtn);

    }

    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify login functionality with correct credentials")
    public void loginAllValid() throws InterruptedException {
        testLoginByGivenCredentials("un_correct", "pw_correct");
        // assert that dashboard page is loaded
        UiAssertionUtils.assertPageTitle(driver, "Yathra Dashboard");
        BrowserUtils.printPageTitle(driver);

    }

    @Test(priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify login functionality with correct username and incorrect password")
    public void loginInvalidPassword() {
        testLoginByGivenCredentials("un_correct", "pw_incorrect");
        // assert that dashboard page did NOT load
        UiAssertionUtils.assertPageTitleNot(driver, "Yathra Dashboard");
    }

    @Test(priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify login functionality with incorrect username and correct password")
    public void loginInvalidUsername() {
        testLoginByGivenCredentials("un_incorrect", "pw_correct");
        UiAssertionUtils.assertPageTitleNot(driver, "Yathra Dashboard");
    }

    @Test(priority = 4)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify login functionality with incorrect username and incorrect password")
    public void loginAllInvalid() {
        testLoginByGivenCredentials("un_incorrect", "pw_incorrect");
        UiAssertionUtils.assertPageTitleNot(driver, "Yathra Dashboard");
    }

}
