package com.automation.framework.api;

import org.testng.annotations.Test;

import com.automation.framework.config.ConfigReader;
import com.automation.framework.utils.AssertionUtils;
import com.automation.framework.utils.HttpStatusCode;
import com.automation.framework.utils.JsonReader;

import io.restassured.response.Response;

public class UpdateUserTest {

    private static final String ENDPOINT_URL = ConfigReader.getProperty("put.endpoint.url");

    //mvn test -Dtest=UpdateUserTest#basicPutReq ✅
    @Test
    public void basicPutReq() {

        String requestBody = JsonReader.readJsonFile("src/test/resources/data/UserData.json");

        Response response = ApiUtils.putRequestWithRawJson(ENDPOINT_URL, requestBody);

        // check body
        System.out.println(response.getBody().asPrettyString());

        // test status code
        //int statusCode = response.getStatusCode();
        AssertionUtils.assertStatusCode(response, HttpStatusCode.OK.getCode());

         System.out.println("basicPutReq Test ran successfully.");
    }

}
