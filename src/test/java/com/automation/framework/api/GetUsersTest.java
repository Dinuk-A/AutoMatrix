package com.automation.framework.api;

import org.testng.annotations.Test;

import com.automation.framework.api.ApiUtils;
import com.automation.framework.config.ConfigReader;
import com.automation.framework.utils.AssertionUtils;
import com.automation.framework.utils.CommonMethods;
import com.automation.framework.utils.HttpStatusCode;
import io.qameta.allure.*;

import org.testng.Assert;

import io.restassured.response.Response;

//to clear prrevious allur jsons >> Remove-Item -Recurse -Force .\allure-results; mvn test -Dtest=GetUsersTest

//mvn test -Dtest=GetUsersTest  ✅✅✅
public class GetUsersTest {

    // capture the endpoint's URL
    private static final String ENDPOINT_URL = ConfigReader.getProperty("endpoint.url");    

    // for allur
    //@Epic("testing allur, user mgt")
    //@Feature("User Retrieval")
    //@Story("Fetch all users from the system")
    //@Severity(SeverityLevel.NORMAL)

    @Test
    //(description = "Verify that all users can be fetched successfully and response is valid")
    public void getAllUersTest() {

        // get the response
        Response response = ApiUtils.getRequest(ENDPOINT_URL);
        System.out.println("👉 Loaded endpoint URL: " + ENDPOINT_URL);

        // print the response body 💥
        CommonMethods.printResponseBody(response);

        AssertionUtils.assertStatusCode(response, HttpStatusCode.OK.getCode());

        // new 💥
        AssertionUtils.assertContentType(response, "application/json");

        System.out.println("Test ran successfully.");

    }
}

// verify the status code
// int responseCode = response.getStatusCode();
// new💥

// Assert the response time is under 5 seconds (5000 ms)
// long responseTime = response.getTime();
// AssertionUtils.assertResponseTime(responseTime, 5000);

// Assert that the content type is JSON original
// String contentType = response.getHeader("Content-Type");
// AssertionUtils.assertContentType(contentType, "application/json");

// ASSERTION WALATATH WENAMA METHODS
// 4 tama

// POST ekedi check karanna one ewa ???