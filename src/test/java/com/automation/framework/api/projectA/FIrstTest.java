package com.automation.framework.api.projectA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.Reporter;

import com.automation.framework.api.ApiUtils;
import com.automation.framework.config.ConfigReader;
import com.automation.framework.utils.AssertionUtils;
import com.automation.framework.utils.CommonMethods;
import com.automation.framework.utils.HttpStatusCode;
import com.automation.framework.utils.JsonReaderNew;

import io.restassured.response.Response;

//mvn test -Dtest=FIrstTest
public class FIrstTest {

    // to save and use the access token later
    // mehema karanna wenne or mekatama class ekak hadana eka 💥💥💥💥💥
    private static String accessToken;

    private static final String SEMP_BASE_URI = ConfigReader.getProperty("sempsarc.base.url");

    private static final String ENDPOINT_URL = ConfigReader.getProperty("sempsarc.post.url");

    private static final String SEMP_GET_BASE_URL = ConfigReader.getProperty("sempsarc.get.base.url");

    private static final String SEMP_GET_END_URL = ConfigReader.getProperty("sempsarc.get.endpoint");

    ///mvn clean

    // mvn test -Dtest=FIrstTest#retrieveTokenByPost
    @Test
    public void retrieveTokenByPost() {

        // me widiyata thamay data denna wenne 💥💥💥💥💥
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("clientId", "MecNecILet@Met001");
        placeholders.put("password", "d2nEH8G4dj");
        placeholders.put("userId", "363538");

        String reqBody = JsonReaderNew.readDynamicJsonFiles("src/test/resources/data/SempsarcBody.json", placeholders);

        Response response = ApiUtils.postReqWithRawJsonNew(SEMP_BASE_URI, ENDPOINT_URL, reqBody);

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
    public void sempsarcGetStreamWithTimeout() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("token", accessToken);

        Response response = ApiUtils.getReqWithQueryParamsForSempSarc(SEMP_GET_BASE_URL, SEMP_GET_END_URL, queryParams);

        AssertionUtils.assertStatusCode(response, HttpStatusCode.OK.getCode());

        int lineCount = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.asInputStream()))) {
            String line;
            long startTime = System.currentTimeMillis();
            long duration = 30_000; // 30 seconds

            System.out.println("Reading stream for 30 seconds...");

            while ((line = reader.readLine()) != null) {
                long elapsed = System.currentTimeMillis() - startTime;
                if (elapsed >= duration) {
                    System.out.println("Time limit reached. Stopping stream.");
                    break;
                }

                if (!line.trim().isEmpty()) {
                    System.out.println("Data: " + line); 
                    lineCount++;
                }
            }

        } catch (IOException e) {
            Assert.fail("Stream reading failed", e);
        }

        System.out.println("Stream read completed with " + lineCount + " lines.");

        // ✅ Ensures the test asserts something meaningful
        Assert.assertTrue(lineCount > 0, "No streaming data was received.");
    }

}
