package com.automation.framework.utils.api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.io.InputStream;
import java.util.Map;
import io.restassured.http.ContentType;

public class ApiUtils {

    // Basic GET request
    public static Response getRequest(String baseUrl, String endPoint) {
        return given()
                .baseUri(baseUrl)
                .when()
                .get(endPoint)
                .then()
                .extract().response();
    }

    // GET with query parameters
    public static Response getReqWithQueryParams(String baseUrl, String endPoint, Map<String, String> paramsMap) {
        return given()
                .baseUri(baseUrl)
                .queryParams(paramsMap)
                .when()
                .get(endPoint)
                .then()
                .extract().response();
    }

    // GET with query parameters (Sempsarc-style)
    public static Response getReqWithQueryParamsForSempSarc(String baseUrl, String endPoint, Map<String, String> paramsMap) {
        return given()
                .baseUri(baseUrl)
                .queryParams(paramsMap)
                .when()
                .get(endPoint)
                .then()
                .extract().response();
    }

    // GET request returning InputStream (Sempsarc-style)
    public static InputStream getReqForStreams(String baseUrl, String endPoint, Map<String, String> paramsMap) {
        return given()
                .baseUri(baseUrl)
                .queryParams(paramsMap)
                .when()
                .get(endPoint)
                .then()
                .extract().response().asInputStream();
    }

    // GET request for streaming response (for SSE or chunked)
    public static Response getStreamingResponse(String baseUrl, String endPoint, Map<String, String> paramsMap) {
        return given()
                .baseUri(baseUrl)
                .queryParams(paramsMap)
                .when()
                .get(endPoint);
    }

    // GET with single path param
    public static Response getReqWithSinglePathParam(String baseUrl, String endPoint, String paramKey, String paramValue) {
        return given()
                .baseUri(baseUrl)
                .pathParam(paramKey, paramValue)
                .when()
                .get(endPoint)
                .then()
                .extract().response();
    }

    // GET with multiple path params
    public static Response getReqWithMultiplePathParams(String baseUrl, String endPoint, Map<String, String> pathParams) {
        return given()
                .baseUri(baseUrl)
                .pathParams(pathParams)
                .when()
                .get(endPoint)
                .then()
                .extract().response();
    }

    //############################## POST ##############################

    // POST with raw JSON (string body)
    public static Response postReqWithRawJson(String baseUrl, String endPoint, String requestBodyJson) {
        return given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(requestBodyJson)
                .when()
                .post(endPoint)
                .then()
                .extract().response();
    }

    // POST with headers and body object
    public static Response postReqWithHeadersAndBody(String baseUrl, String endPoint, Map<String, String> headers, Object requestBody) {
        return given()
                .baseUri(baseUrl)
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(endPoint)
                .then()
                .extract().response();
    }

    //############################## PuT ##############################

    // PUT with raw JSON
    public static Response putRequestWithRawJson(String baseUrl, String endPoint, String requestBodyJson) {
        return given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(requestBodyJson)
                .when()
                .put(endPoint)
                .then()
                .extract().response();
    }

    // DELETE request
    public static Response deleteRequest(String baseUrl, String endPoint) {
        return given()
                .baseUri(baseUrl)
                .when()
                .delete(endPoint)
                .then()
                .extract().response();
    }

    // PATCH with raw JSON
    public static Response patchRequestWithRawJson(String baseUrl, String endPoint, String requestBodyJson) {
        return given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(requestBodyJson)
                .when()
                .patch(endPoint)
                .then()
                .extract().response();
    }
}
