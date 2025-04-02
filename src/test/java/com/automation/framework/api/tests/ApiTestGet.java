package com.automation.framework.api.tests;

import org.testng.annotations.Test;

import com.automation.framework.api.utils.GetApi;

import io.restassured.response.Response;

//mvn test -Dtest=ApiTestGet  ✅✅✅
public class ApiTestGet {
    
    @Test
    public void testGetUsers(){

        Response response = GetApi.getResponseBody("/users?page=2");

        System.out.println("Response Body: ");
        System.out.println(response.getBody().asPrettyString());
    }

}
