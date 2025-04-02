package com.automation.framework.api.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

//mvn test -Dtest=FirstApiTest ✅✅✅
public class FirstApiTest {

    @Test
    public void firstApiTest() {

        Response result = RestAssured.get("https://reqres.in/api/users?page=2");

        Integer statusCode = result.getStatusCode();

        System.out.println(result.getBody().asPrettyString());
        System.out.println("Time: " + result.getTime());
        System.out.println("Status Code: " + statusCode);
        System.out.println("Test completed");

    }
}
