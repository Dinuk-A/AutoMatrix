package com.automation.framework.SampleTests.api;

import org.testng.annotations.Test;

import com.automation.framework.utils.api.ApiUtils;
import com.automation.framework.utils.api.AssertionUtils;
import com.automation.framework.utils.api.HttpStatusCode;
import com.automation.framework.utils.common.ConfigReader;

import io.restassured.response.Response;

public class DeleteUserTest {

    private static final String BASE_URL = ConfigReader.getProperty("base.url");

    private static final String ENDPOINT_URL = ConfigReader.getProperty("put.endpoint.url");

    //mvn test -Dtest=DeleteUserTest#deleteRequest
    @Test
    public void deleteRequest() {

        Response response = ApiUtils.deleteRequest(BASE_URL,ENDPOINT_URL);

        AssertionUtils.assertStatusCode(response, HttpStatusCode.NO_CONTENT.getCode());

        AssertionUtils.assertResponseBodyIsEmpty(response.getBody().asString());

        System.out.println("basicPutReq Test ran successfully.");

    }
}
