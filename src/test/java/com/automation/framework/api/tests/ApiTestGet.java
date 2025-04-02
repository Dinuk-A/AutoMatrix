package com.automation.framework.api.tests;

import org.testng.Assert;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.automation.framework.api.utils.GetApi;

import io.restassured.response.Response;

//mvn test -Dtest=ApiTestGet  ✅✅✅
public class ApiTestGet {

    @Test
    public void testGetUsers() {

        // end point ekath property file eken ganna one
        Response response = GetApi.getResponseBody("/users?page=2");

        // verify the body
        System.out.println("Response Body: ");
        System.out.println(response.getBody().asPrettyString());

        // MEWA WENA WENAMA METHODS WALA LIYANNADA? 💥💥💥💥
        // common methods for all

        // verify the status code
        Integer responseCode = response.getStatusCode();
        assertEquals(responseCode, 200);

        // USE ENUM FOR STATUS CODE

        // ASSERTION WALATATH WENAMA METHODS
        // 4 tama

        // POST ekedi check karanna one ewa ???

    }

}
