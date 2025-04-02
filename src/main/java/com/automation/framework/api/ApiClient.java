package com.automation.framework.api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import com.automation.framework.config.ConfigReader;


public class ApiClient {
    
    //get the desired base url
    private static final String BASE_URL = ConfigReader.getProperty("base.url");

    //wrapper for GET reqs
    public static Response getRequest(String customEndPoint) {
        return given()
                .baseUri(BASE_URL)
                .when()
                .get(customEndPoint)
                .then()
                .extract().response();

    }

}

    //post , put , delete methods needs to be here too
