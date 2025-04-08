package com.automation.framework.utils;

import io.restassured.response.Response;

public class CommonMethods {

    public static void printResponseBody(Response response) {
        System.out.println("Response Body: ");
        System.out.println(response.getBody().asPrettyString());
    }
}
