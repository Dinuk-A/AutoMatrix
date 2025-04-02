package com.automation.framework.api;

import org.testng.annotations.Test;

import com.automation.framework.api.ApiClient;
import com.automation.framework.config.ConfigReader;
import org.testng.Assert;

import io.restassured.response.Response;

//mvn test -Dtest=GetUsersTest  ✅✅✅
public class GetUsersTest {

    // capture the endpoint's URL
    private static final String ENDPOINT_URL = ConfigReader.getProperty("endpoint.url");

    @Test
    public void getAllUersTest() {

        // get the response
        Response response = ApiClient.getRequest(ENDPOINT_URL);

        // verify the body
        System.out.println("Response Body: ");
        System.out.println(response.getBody().asPrettyString());

        // MEWA WENA WENAMA METHODS WALA LIYANNADA? 💥💥💥💥
        // common methods for all hadenna one

        // verify the status code
        Integer responseCode = response.getStatusCode();
        Assert.assertEquals(responseCode, 200);
        System.out.println(responseCode);
        System.out.println("test ran");

        // USE ENUM FOR STATUS CODE

        // ASSERTION WALATATH WENAMA METHODS
        // 4 tama

        // POST ekedi check karanna one ewa ???

    }
}
