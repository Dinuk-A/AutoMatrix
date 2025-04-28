package com.automation.framework.JsonPlaceholder;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.automation.framework.utils.api.ApiUtils;
import com.automation.framework.utils.api.AssertionUtils;
import com.automation.framework.utils.api.CommonMethods;
import com.automation.framework.utils.api.HttpStatusCode;
import com.automation.framework.utils.api.JsonUtils;
import com.automation.framework.utils.common.ConfigReader;

import io.restassured.response.Response;

public class PutTest {

    private static final String BASE_URL = ConfigReader.getProperty("jp.base.url");

    private static final String ENDPOINT_URL = ConfigReader.getProperty("jp.put.delete.url");

    //✅
    //https://jsonplaceholder.typicode.com/posts/1
    @Test
    public void basicPutTest() {

        Map<String, Object> data = new HashMap<>();
        data.put("id", 1);
        data.put("title", "Updated Title from Test");
        data.put("body", "This is updated via automation script");
        data.put("userId", 1);

        String requestBody = JsonUtils.createJsonFromMap(data);

        Response response = ApiUtils.putRequestWithRawJson(BASE_URL, ENDPOINT_URL, requestBody);

        CommonMethods.printResponseBody(response);

        AssertionUtils.assertStatusCode(response, HttpStatusCode.OK.getCode());

        System.out.println("basicPutTest success");

    }
}
