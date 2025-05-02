package com.automation.framework.JsonPlaceholder;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.automation.framework.utils.api.ApiUtils;
import com.automation.framework.utils.api.AssertionUtils;
import com.automation.framework.utils.api.CommonMethods;
import com.automation.framework.utils.api.HttpStatusCode;
import com.automation.framework.utils.api.JsonUtils;
import com.automation.framework.utils.common.ConfigPropertyReader;
import com.automation.framework.utils.common.JsonReader;

import io.restassured.response.Response;

public class PostTest {

    private static final String BASE_URL = ConfigPropertyReader.getProperty("jp.base.url");

    private static final String ENDPOINT_URL = ConfigPropertyReader.getProperty("jp.get.post.endpoint.url");

    // ✅
    // https://jsonplaceholder.typicode.com/posts
    @Test
    public void postWithStaticJson() {

        String requestBody = JsonReader.readStaticJsonFile("src/test/resources/data/PredefinedEx.json");

        Response response = ApiUtils.postReqWithRawJson(BASE_URL, ENDPOINT_URL, requestBody);

        CommonMethods.printResponseBody(response);

        AssertionUtils.assertStatusCode(response, HttpStatusCode.CREATED.getCode());

        System.out.println("postWithStaticJson success");

    }

    // ✅
    // https://jsonplaceholder.typicode.com/posts
    @Test
    public void postWithDynamicBody() {

        Map<String, Object> jsonBodyMap = new HashMap<>();
        jsonBodyMap.put("title", "New Post");
        jsonBodyMap.put("body", "This is a new post created for testing");
        jsonBodyMap.put("userId", "1");

        String reqBody = JsonUtils.createJsonFromMap(jsonBodyMap);

        Response response = ApiUtils.postReqWithRawJson(BASE_URL, ENDPOINT_URL, reqBody);

        CommonMethods.printResponseBody(response);

        AssertionUtils.assertStatusCode(response, HttpStatusCode.CREATED.getCode());

        System.out.println("postWithDynamicBody success");

    }
}
