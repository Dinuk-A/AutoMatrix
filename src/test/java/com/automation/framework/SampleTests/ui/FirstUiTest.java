package com.automation.framework.SampleTests.ui;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.framework.Core.ui.BaseTest;

public class FirstUiTest extends BaseTest{
    
    @Test
    public void openHerokuTest(){
        openBaseUrl("https://the-internet.herokuapp.com/");
        String title = driver.getTitle();
        Assert.assertEquals(title, "The Internet");
        System.out.println("title: " + title);
    }

}
