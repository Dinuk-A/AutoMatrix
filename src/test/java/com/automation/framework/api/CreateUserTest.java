package com.automation.framework.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.framework.config.ConfigReader;
import com.automation.framework.utils.AssertionUtils;
import com.automation.framework.utils.HttpStatusCode;
import com.automation.framework.utils.JsonReader;
import com.automation.framework.utils.JsonReaderNew;

import io.restassured.response.Response;

//mvn test -Dtest=CreateUserTest ✅
public class CreateUserTest {

    private static final String ENDPOINT_URL = ConfigReader.getProperty("post.endpoint.url");

    // mvn test -Dtest=CreateUserTest#basicPostReq ✅
    @Test
    public void basicPostReq() {
        String requestBody = JsonReader.readJsonFile("src/test/resources/data/UserData.json");
        Response response = ApiUtils.postReqWithRawJson(ENDPOINT_URL, requestBody);

        // check body
        System.out.println(response.getBody().asPrettyString());

        // test status code
        //int statusCode = response.getStatusCode();
        AssertionUtils.assertStatusCode(response, HttpStatusCode.CREATED.getCode());

        // is response time below X seconds ?
        long responseTime = response.getTime();
        AssertionUtils.assertResponseTime(responseTime, 5000);

        // content-Type Check using AssertionUtils
        //String contentType = response.getHeader("Content-Type");
        AssertionUtils.assertContentType(response, "application/json");

        // NO ASSERTUTIL METHOD for this 💥
        // check the returned user details matches the exact values
        String userName = response.jsonPath().getString("name");
        Assert.assertEquals("Dinuka Pramod", userName);

        System.out.println("basicPostReq Test ran successfully.");

    }

    //mvn test -Dtest=CreateUserTest#dynamicPostReqTest
    @Test
    public void dynamicPostReqTest() {
        
        String reqBody = JsonReaderNew.readJsonFile("src/test/resources/data/UserDataNew.json", "John Doe",
                "Software Engineer");

        Response response = ApiUtils.postReqWithRawJson(ENDPOINT_URL, reqBody);

        System.out.println(response.getBody().asPrettyString());

        // Test status code
        AssertionUtils.assertStatusCode(response, HttpStatusCode.CREATED.getCode());

        System.out.println("dynamicPostReqTest success");
    }

}
