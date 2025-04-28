package com.automation.framework.JsonPlaceholder;

import org.testng.annotations.Test;

import com.automation.framework.utils.api.ApiUtils;
import com.automation.framework.utils.api.AssertionUtils;
import com.automation.framework.utils.api.CommonMethods;
import com.automation.framework.utils.api.HttpStatusCode;
import com.automation.framework.utils.common.ConfigReader;

import io.restassured.response.Response;

public class GetTest {

    private static final String BASE_URL = ConfigReader.getProperty("jp.base.url");

    private static final String ENDPOINT_URL = ConfigReader.getProperty("jp.get.endpoint.url");

    //✅
    //https://jsonplaceholder.typicode.com/posts
    @Test
    public void basicGetTest() {
        Response response = ApiUtils.getRequest(BASE_URL, ENDPOINT_URL);
        CommonMethods.printResponseBody(response);
        AssertionUtils.assertStatusCode(response, HttpStatusCode.OK.getCode());
        System.out.println("Test ran successfully.");
    }

}
