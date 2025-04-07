package com.automation.framework.api;

import org.testng.annotations.Test;

import com.automation.framework.config.ConfigReader;
import com.automation.framework.utils.AssertionUtils;
import com.automation.framework.utils.HttpStatusCode;

import io.restassured.response.Response;

public class DeleteUserTest {

    private static final String ENDPOINT_URL = ConfigReader.getProperty("put.endpoint.url");

    //mvn test -Dtest=DeleteUserTest#deleteRequest
    //no body returned
    @Test
    public void deleteRequest() {

        Response response = ApiUtils.deleteRequest(ENDPOINT_URL);

        int statusCode = response.getStatusCode();
        AssertionUtils.assertStatusCode(statusCode, HttpStatusCode.NO_CONTENT.getCode());

        AssertionUtils.assertResponseBodyIsEmpty(response.getBody().asString());

        System.out.println("basicPutReq Test ran successfully.");

    }
}
