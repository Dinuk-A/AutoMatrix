package com.automation.framework.ProjectA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.automation.framework.api.ApiUtils;
import com.automation.framework.config.ConfigReader;
import com.automation.framework.utils.AssertionUtils;
import com.automation.framework.utils.CommonMethods;
import com.automation.framework.utils.HttpStatusCode;
import com.automation.framework.utils.JsonReaderNew;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

//Remove-Item -Recurse -Force .\allure-results; mvn test -Dtest=Sec
public class Sec {
    // to save and use the access token later
    // private static String accessToken;

    private static final String SEMP_BASE_URI = ConfigReader.getProperty("sempsarc.base.url");

    private static final String ENDPOINT_URL = ConfigReader.getProperty("sempsarc.post.url");

    private static final String SEMP_GET_BASE_URL = ConfigReader.getProperty("sempsarc.get.base.url");

    private static final String SEMP_GET_END_URL = ConfigReader.getProperty("sempsarc.get.endpoint");

    @Test
    public void retrieveTokenAndReadStream() {
        // 1. Retrieve token
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("clientId", "MecNecILet@Met001");
        placeholders.put("password", "d2nEH8G4dj");
        placeholders.put("userId", "363538");

        String reqBody = JsonReaderNew.readDynamicJsonFiles("src/test/resources/data/SempsarcBody.json", placeholders);
        Response response = ApiUtils.postReqWithRawJson(SEMP_BASE_URI, ENDPOINT_URL, reqBody);
        String accessToken = response.jsonPath().getString("data.accessToken");

        System.out.println("Access Token: " + accessToken);

        // 2. Start stream with token
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("token", accessToken);

        InputStream stream = ApiUtils.getReqForStreams(SEMP_GET_BASE_URL, SEMP_GET_END_URL, queryParams);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        System.out.println("Reading stream for 5 seconds...");

        long start = System.currentTimeMillis();
        long duration = 5000; // 5 seconds

        try {
            String line;
            while ((System.currentTimeMillis() - start) < duration && (line = reader.readLine()) != null) {
                System.out.println("Stream: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close(); // 🛑 Force close stream
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Stream closed ✅");

        // 3. Make a quick GET to get status
        //int statusCode = given()
        //        .baseUri(SEMP_GET_BASE_URL)
        //        .queryParams(queryParams)
        //        .when()
        //        .get(SEMP_GET_END_URL)
        //        .getStatusCode();

        //System.out.println("Status Code: " + statusCode);
    }

}


//Remove-Item -Recurse -Force .\allure-results; mvn test -Dtest=Sec