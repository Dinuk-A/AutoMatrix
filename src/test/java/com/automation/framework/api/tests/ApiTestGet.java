package com.automation.framework.api.tests;

import org.testng.Assert;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.automation.framework.api.utils.ConfigReader;
import com.automation.framework.api.utils.GetApi;

import io.restassured.response.Response;

//mvn test -Dtest=ApiTestGet  ✅✅✅
public class ApiTestGet {

     // capture the endpoint's URL
     private static final String ENDPOINT_URL = ConfigReader.getProperty("endpoint.url");

    @Test
    public void testGetUsers() {

        // end point ekath property file eken ganna one

       

        // get the response
        Response response = GetApi.getResponseBody(ENDPOINT_URL);

        // verify the body
        System.out.println("Response Body: ");
        System.out.println(response.getBody().asPrettyString());

        // MEWA WENA WENAMA METHODS WALA LIYANNADA? 💥💥💥💥
        // common methods for all hadenna one

        // verify the status code
        Integer responseCode = response.getStatusCode();
        assertEquals(responseCode, 200);
        System.out.println(responseCode);

        // USE ENUM FOR STATUS CODE

        // ASSERTION WALATATH WENAMA METHODS
        // 4 tama

        // POST ekedi check karanna one ewa ???

    }

}
