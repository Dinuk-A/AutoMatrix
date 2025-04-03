package com.automation.framework.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.framework.config.ConfigReader;
import com.automation.framework.utils.AssertionUtils;
import com.automation.framework.utils.HttpStatusCode;
import com.automation.framework.utils.JsonReader;

import io.restassured.response.Response;

//mvn test -Dtest=CreateUserTest ✅
public class CreateUserTest {

    private static final String ENDPOINT_URL = ConfigReader.getProperty("post.endpoint.url");

    // mvn test -Dtest=CreateUserTest#basicPostReq ✅
    @Test
    public void basicPostReq() {
        String requestBody = JsonReader.readJsonFile("src/test/resources/data/UserData.json");
        Response response = ApiClient.postReqWithRawJson(ENDPOINT_URL, requestBody);

        //values manual denna 💥💥💥
        //json eke value nathath kamak na
        //dif folders for dif projects 💥💥💥

        // check body
        System.out.println(response.getBody().asPrettyString());

        // test status code
        int statusCode = response.getStatusCode();
        AssertionUtils.assertStatusCode(statusCode, HttpStatusCode.CREATED.getCode());

        // is response time below 2 seconds ?
        long responseTime = response.getTime();
        AssertionUtils.assertResponseTime(responseTime, 2000);

        // content-Type Check using AssertionUtils
        String contentType = response.getHeader("Content-Type");
        AssertionUtils.assertContentType(contentType, "application/json");

        //NO ASSERTUTIL METHOD for this 💥
        // check the returned user details matches the exact values
        String userName = response.jsonPath().getString("name");
        Assert.assertEquals("Dinuka Pramod", userName);

        System.out.println("basicPostReq Test ran successfully.");

    }


 

}
