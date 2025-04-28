package com.automation.framework.SampleTests.api;

import org.testng.annotations.Test;

import com.automation.framework.utils.api.ApiUtils;
import com.automation.framework.utils.api.AssertionUtils;
import com.automation.framework.utils.api.HttpStatusCode;
import com.automation.framework.utils.api.JsonReader;
import com.automation.framework.utils.common.ConfigReader;

import io.restassured.response.Response;

public class UpdateUserTest {

    private static final String BASE_URL = ConfigReader.getProperty("base.url");

    private static final String ENDPOINT_URL = ConfigReader.getProperty("put.endpoint.url");

    //mvn test -Dtest=UpdateUserTest#basicPutReq ✅
    @Test
    public void basicPutReq() {

        String requestBody = JsonReader.readJsonFile("src/test/resources/data/UserData.json");

        Response response = ApiUtils.putRequestWithRawJson(BASE_URL,ENDPOINT_URL, requestBody);

        // check body
        System.out.println(response.getBody().asPrettyString());

        // test status code
        //int statusCode = response.getStatusCode();
        AssertionUtils.assertStatusCode(response, HttpStatusCode.OK.getCode());

         System.out.println("basicPutReq Test ran successfully.");
    }

}
