package com.automation.framework.ProjectA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;

import com.automation.framework.utils.api.ApiUtils;
import com.automation.framework.utils.api.AssertionUtils;
import com.automation.framework.utils.api.CommonMethods;
import com.automation.framework.utils.api.HttpStatusCode;
import com.automation.framework.utils.api.JsonReaderNew;
import com.automation.framework.utils.common.ConfigReader;

import io.restassured.response.Response;

//Remove-Item -Recurse -Force .\allure-results; mvn test -Dtest=FIrstTest
//mvn test -Dtest=FIrstTest
public class FIrstTest {

    // to save and use the access token later
       private static String accessToken;

    private static final String SEMP_BASE_URI = ConfigReader.getProperty("sempsarc.base.url");

    private static final String ENDPOINT_URL = ConfigReader.getProperty("sempsarc.post.url");

    private static final String SEMP_GET_BASE_URL = ConfigReader.getProperty("sempsarc.get.base.url");

    private static final String SEMP_GET_END_URL = ConfigReader.getProperty("sempsarc.get.endpoint");

    /// mvn clean

    // mvn test -Dtest=FIrstTest#retrieveTokenByPost
    @Test
    public void retrieveTokenByPost() {

        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("clientId", "MecNecILet@Met001");
        placeholders.put("password", "d2nEH8G4dj");
        placeholders.put("userId", "363538");

        String reqBody = JsonReaderNew.readDynamicJsonFiles("src/test/resources/data/SempsarcBody.json", placeholders);

        Response response = ApiUtils.postReqWithRawJson(SEMP_BASE_URI, ENDPOINT_URL, reqBody);

        // verify the body
        CommonMethods.printResponseBody(response);

        // Test status code
        AssertionUtils.assertStatusCode(response, HttpStatusCode.OK.getCode());
        // POST wala 201 (created) enna one, but meke enne 200(OK). ay ?💥💥💥

        // retrieve from here
        accessToken = response.jsonPath().getString("data.accessToken");
        System.out.println("Access Token: " + accessToken);

        System.out.println("retrieveTokenByPost success");

    }

    // GET for sempsarc
    @Test
    public void retrieveDataWithToken() {
        // Ensure accessToken is not null
        if (accessToken == null || accessToken.isEmpty()) {
            System.out.println("Access token is missing. Run retrieveTokenByPost() first.");
            return;
        }

        // Set query param with the token
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("token", accessToken);

        // Get the streaming response
        InputStream responseStream = ApiUtils.getReqForStreams(SEMP_GET_BASE_URL, SEMP_GET_END_URL, queryParams);

        // Read from stream for a few seconds
        long startTime = System.currentTimeMillis();
        long maxDuration = 5000; // 5 seconds

        System.out.println("Reading stream for 5 seconds... ");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream))) {
            String line;
            while ((line = reader.readLine()) != null && (System.currentTimeMillis() - startTime < maxDuration)) {
                System.out.println("Stream: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Stream closed ");
    }

}

// Remove-Item -Recurse -Force .\allure-results; mvn test -Dtest=FIrstTest