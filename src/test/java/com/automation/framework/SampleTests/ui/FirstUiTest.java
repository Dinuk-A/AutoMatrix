package com.automation.framework.SampleTests.ui;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.framework.Utils.ui.BaseTest;
import com.automation.framework.utils.ui.BrowserUtils;

public class FirstUiTest extends BaseTest{
    
    @Test
    public void openHerokuTest(){
        BrowserUtils.openUrl(driver,"https://the-internet.herokuapp.com/");
        String title = driver.getTitle();
        Assert.assertEquals(title, "The Internet");
        System.out.println("title: " + title);
    }

}
