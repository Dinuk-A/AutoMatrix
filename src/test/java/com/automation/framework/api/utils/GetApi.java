package com.automation.framework.api.utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetApi {

    private static final String BASE_URL = ConfigReader.getProperty("base.url");
    
    public static Response getResponseBody(String customEndPoint){
        return given()
        .baseUri(BASE_URL)
        .when()
        .get(customEndPoint)
        .then()
        .extract().response();

    }

}
