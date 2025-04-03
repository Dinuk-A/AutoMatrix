package com.automation.framework.api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.Map;

import com.automation.framework.config.ConfigReader;

import io.restassured.http.ContentType;


public class ApiClient {

    // this is for basic GETs
    private static final String BASE_URL = ConfigReader.getProperty("base.url");

    // this is for query param related GETs
    private static final String BASE_URL_JSON_PLACEHOLDER = ConfigReader.getProperty("qp.url");

    // wrapper for BASIC GET reqs ✅
    public static Response getRequest(String customEndPoint) {
        return given()
                .baseUri(BASE_URL)
                .when()
                .get(customEndPoint)
                .then()
                .extract().response();
    }

    // wrapper for GET with QUERY PARAMS ( single & multi ) ✅
    // ex: https://api.example.com/users?status=active&role=admin
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
    // https://reqres.in/api/users/4
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
    // ??? 💥💥💥
    public static Response getReqWithMultiplePathParams(String customEndPoint, Map<String, String> pathParams) {
        return given()
                .baseUri(BASE_URL)
                .pathParams(pathParams)
                .when()
                .get(customEndPoint)
                .then()
                .extract().response();
    }

    // wrapper for no-payload, empty postreq??? 💥💥💥

    // ##########################################################################

    // POSTS ✅✅✅
    // wrapper for basic POST with a json payload read from a file
    public static Response postReqWithRawJson(String customEndPoint, String requestBodyJson) {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(requestBodyJson)
                .when()
                .post(customEndPoint)
                .then()
                .extract().response();
    }

    // wrapper for POST with a header + payload
    public static Response postReqWithHeadersAndBody(String customEndPoint, Map<String, String> headers, Object requestBody) {
        return given()
                .baseUri(BASE_URL)
                .headers(headers) 
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(customEndPoint)
                .then()
                .extract().response();
    }

}

// post , put , delete methods needs to be here too
