package com.automation.framework.SampleTests.api;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.automation.framework.api.ApiUtils;
import com.automation.framework.config.ConfigReader;
import com.automation.framework.utils.AssertionUtils;
import com.automation.framework.utils.HttpStatusCode;

import io.restassured.response.Response;

//mvn test -Dtest=GetUserByParamsTest ✅
public class GetUserByParamsTest {

    private static final String BASE_URL = ConfigReader.getProperty("base.url");

    private static final String BASE_URL_JSON_PLACEHOLDER = ConfigReader.getProperty("qp.url");

    // capture the endpoint's URL
    private static final String QP_ENDPOINT_URL = ConfigReader.getProperty("qp.endpoint.url");
    private static final String PP_ENDPOINT_URL = ConfigReader.getProperty("single.path.param.url");

    // 1 === single query params
    // https://jsonplaceholder.typicode.com/posts?userId=1
    // mvn test -Dtest=GetUserByParamsTest#getUserBySingleQueryParam ✅
    @Test
    public void getUserBySingleQueryParam() {

        // create query param values
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("userId", "1");

        // call wrapper class
        Response response = ApiUtils.getReqWithQueryParams(BASE_URL_JSON_PLACEHOLDER,QP_ENDPOINT_URL, queryParams);

        // print the body
        System.out.println(response.getBody().asString());

        // verify the status code
        //int responseCode = response.getStatusCode();
        AssertionUtils.assertStatusCode(response, HttpStatusCode.OK.getCode());

        System.out.println("getUserBySingleQueryParam Test ran successfully.");

    }

    // 1 === multiple query params
    // https://jsonplaceholder.typicode.com/posts?userId=1&id=4
    // mvn test -Dtest=GetUserByParamsTest#getUserByMultiQueryParams ✅
    @Test
    public void getUserByMultiQueryParams() {
        // create query param values
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("userId", "1");
        queryParams.put("id", "4");

        // call wrapper class
        Response response = ApiUtils.getReqWithQueryParams(BASE_URL_JSON_PLACEHOLDER,QP_ENDPOINT_URL, queryParams);

        // print the body
        System.out.println(response.getBody().asString());

        // verify the status code
        AssertionUtils.assertStatusCode(response, HttpStatusCode.OK.getCode());

        System.out.println(" getUserByMultiQueryParams Test ran successfully.");

    }

    // 3 === single path param
    // https://reqres.in/api/users/4
    // mvn test -Dtest=GetUserByParamsTest#getUserBySinglePathParam ✅
    @Test
    public void getUserBySinglePathParam() {
        // call wrapper class
        Response response = ApiUtils.getReqWithSinglePathParam(BASE_URL,PP_ENDPOINT_URL, "id", "4");

        // print the body
        System.out.println(response.getBody().asString());

        // verify the status code
        //int responseCode = response.getStatusCode();
        AssertionUtils.assertStatusCode(response, HttpStatusCode.OK.getCode());

        System.out.println(" getUserBySinglePathParam Test ran successfully.");

    }








    // 4 === multiple path param 💥💥💥
    // 
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
