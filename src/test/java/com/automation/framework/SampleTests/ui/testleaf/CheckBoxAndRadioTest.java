package com.automation.framework.SampleTests.ui.testleaf;

import com.automation.framework.utils.ui.BrowserUtils;
import com.automation.framework.utils.ui.ElementActions;
import com.automation.framework.utils.ui.WaitUtils;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.framework.Utils.ui.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Epic("UI Automation")
@Feature("TestLeaf Module")
@Story("Check Box and Radio Validation")
public class CheckBoxAndRadioTest extends BaseTest {

    ElementActions actions = new ElementActions(driver);

    @BeforeMethod
    public void navigateToWebPage() {
        Path path = Paths.get("src/test/resources/practice-htmls/cb_radio_test.html");
        String fileUrl = path.toAbsolutePath().toUri().toString();
        BrowserUtils.openUrl(driver, fileUrl);
    }

    // check box
    @Test(enabled = true, priority = 1, description = "Validate that the user can interact with checkboxes")
    public void checkboxesTest() throws InterruptedException {

        By hobbyCheckBoxLocator = By.id("hobbyTraveling");
        WebElement hobbyCB = WaitUtils.waitForElementClickable(driver, hobbyCheckBoxLocator);
        actions.click(hobbyCB);

        WebElement gamingCB = WaitUtils.waitForElementClickable(driver, By.id("hobbyGaming"));
        actions.click(gamingCB);

    }

    // radio
    @Test(enabled = true, priority = 2, description = "Validate that the user can interact with radio buttons")
    public void radioTest() throws InterruptedException {

        WebElement maleRadio = WaitUtils.waitForElementClickable(driver, By.id("genderMale"));
        actions.click(maleRadio);

        WebElement femaleRadio = WaitUtils.waitForElementClickable(driver, By.id("genderFemale"));
        actions.click(femaleRadio);
    }

}
