package com.automation.framework.SampleTests.api;

import org.testng.annotations.Test;

import com.automation.framework.utils.api.ApiUtils;
import com.automation.framework.utils.api.AssertionUtils;
import com.automation.framework.utils.api.CommonMethods;
import com.automation.framework.utils.api.HttpStatusCode;
import com.automation.framework.utils.common.ConfigReader;

import io.qameta.allure.*;
import io.restassured.response.Response;

//to clear prrevious allur jsons >> Remove-Item -Recurse -Force .\allure-results; mvn test -Dtest=GetUsersTest

//mvn test -Dtest=GetUsersTest  ✅✅✅
//mvn test -Dtest=GetUsersTest -DskipTests=true
public class GetUsersTest {

    // capture the endpoint's URL
    private static final String ENDPOINT_URL = ConfigReader.getProperty("endpoint.url");    

    private static final String BASE_URL = ConfigReader.getProperty("base.url");

    // for allur
    @Epic("testing allur, user mgt")
    @Feature("User Retrieval")
    @Story("Fetch all users from the system")
    @Severity(SeverityLevel.NORMAL)

    @Test
    //(description = "Verify that all users can be fetched successfully and response is valid")
    public void getAllUersTest() {

        Response response = ApiUtils.getRequest(BASE_URL, ENDPOINT_URL);
        //System.out.println(" Loaded endpoint URL: " + ENDPOINT_URL);

        CommonMethods.printResponseBody(response);

        AssertionUtils.assertStatusCode(response, HttpStatusCode.OK.getCode());

        AssertionUtils.assertContentType(response, "application/json");

        System.out.println("Test ran successfully.");

    }
}



// Assert the response time is under 5 seconds (5000 ms)
// long responseTime = response.getTime();
// AssertionUtils.assertResponseTime(responseTime, 5000);

// Assert that the content type is JSON original
// String contentType = response.getHeader("Content-Type");
// AssertionUtils.assertContentType(contentType, "application/json");

