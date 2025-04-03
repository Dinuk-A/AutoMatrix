package com.automation.framework.api;

import org.testng.annotations.Test;

import com.automation.framework.config.ConfigReader;
import com.automation.framework.utils.JsonReader;

import io.restassured.response.Response;

//mvn test -Dtest=CreateUserTest ✅
public class CreateUserTest {

    private static final String ENDPOINT_URL = ConfigReader.getProperty("post.endpoint.url");

    //mvn test -Dtest=CreateUserTest#basicPostReq ✅
    @Test
    public void basicPostReq() {
        String requestBody = JsonReader.readJsonFile("src/test/resources/data/UserData.json");
        Response response = ApiClient.postReqWithRawJson(ENDPOINT_URL, requestBody);
        System.out.println(response.getBody().asPrettyString());
    }
}
