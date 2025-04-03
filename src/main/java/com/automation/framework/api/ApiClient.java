package com.automation.framework.api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.Map;

import com.automation.framework.config.ConfigReader;

public class ApiClient {

    // get the desired base url
    private static final String BASE_URL = ConfigReader.getProperty("base.url");
    private static final String BASE_URL_JSON_PLACEHOLDER = ConfigReader.getProperty("jp.url");

    // wrapper for BASIC GET reqs ✅
    public static Response getRequest(String customEndPoint) {
        return given()
                .baseUri(BASE_URL)
                .when()
                .get(customEndPoint)
                .then()
                .extract().response();

    }

    // wrapper for GET with MULTI QUERY PARAMS
    // https://api.example.com/users?status=active&role=admin
    // use json place holder typicode for test
    //https://jsonplaceholder.typicode.com/posts?userId=1
    public static Response getReqWithQueryParams(String customEndPoint, Map<String, String> paramsMap) {
        Response response = given()
                .baseUri(BASE_URL_JSON_PLACEHOLDER)
                .queryParams(paramsMap)
                .when()
                .get(customEndPoint)
                .then()
                .extract().response();
        return response;
    }

    // wrapper for GET with SINGLE PATH PARAM
    // https://api.example.com/users/123
    // use req res for test
    public static Response getReqWithSinglePathParam(String customEndPoint, String paramKey, String paramValue) {
        return given()
                .baseUri(BASE_URL)
                .pathParam(paramKey, paramValue)
                .when()
                .get(customEndPoint)
                .then()
                .extract().response();
    }

    // wrapper for GET with MULTI PATH PARAM
    // GET https://api.example.com/users/123/orders/456

    public static Response getReqWithMultiplePathParams(String customEndPoint, Map<String, String> pathParams) {
        return given()
                .baseUri(BASE_URL)
                .pathParams(pathParams)
                .when()
                .get(customEndPoint)
                .then()
                .extract().response();
    }

}

// post , put , delete methods needs to be here too
