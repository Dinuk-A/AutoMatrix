package com.automation.framework.api;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.automation.framework.config.ConfigReader;
import com.automation.framework.utils.AssertionUtils;
import com.automation.framework.utils.HttpStatusCode;

import io.restassured.response.Response;

//mvn test -Dtest=GetUserByParamsTest
public class GetUserByParamsTest {

    // capture the endpoint's URL
    private static final String QP_ENDPOINT_URL = ConfigReader.getProperty("qp.endpoint.url");

    // 1
    // query params
    // https://jsonplaceholder.typicode.com/posts?userId=1 ✅

    @Test
    public void getUserBySingleQueryParam() {

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("userId", "1");

        Response response = ApiClient.getReqWithQueryParams(QP_ENDPOINT_URL, queryParams);

        System.out.println(response.getBody().asString());

        // verify the status code
        int responseCode = response.getStatusCode();
        AssertionUtils.assertStatusCode(responseCode, HttpStatusCode.OK.getCode());
        System.out.println(responseCode);

    }

    // queryParams.put("id", "4");

    // 2
    // single path param
    /*
     * Response response = getReqWithSinglePathParam("/users/{userId}", "userId",
     * "123");
     * System.out.println(response.getBody().asString());
     * 
     */

    // 3
    // multiple path param
    /*
     * Map<String, String> pathParams = new HashMap<>();
     * pathParams.put("userId", "123");
     * pathParams.put("orderId", "456");
     * 
     * Response response =
     * getReqWithMultiplePathParams("/users/{userId}/orders/{orderId}", pathParams);
     * System.out.println(response.getBody().asString());
     * 
     */

}
