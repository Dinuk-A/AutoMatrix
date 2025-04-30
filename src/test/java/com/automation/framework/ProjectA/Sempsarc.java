package com.automation.framework.ProjectA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.automation.framework.utils.api.ApiUtils;
import com.automation.framework.utils.api.JsonReaderNew;
import com.automation.framework.utils.common.ConfigReader;

import io.restassured.response.Response;
import io.qameta.allure.*;

@Epic("SEMP Streaming API")
@Feature("Combined Token Retrieval and Stream Reading")
public class Sempsarc {

    private static final String SEMP_BASE_URL = ConfigReader.getProperty("sempsarc.base.url");
    private static final String ENDPOINT_URL = ConfigReader.getProperty("sempsarc.post.url");
    private static final String SEMP_GET_BASE_URL = ConfigReader.getProperty("sempsarc.get.base.url");
    private static final String SEMP_GET_END_URL = ConfigReader.getProperty("sempsarc.get.endpoint");

    @Test(description = "Retrieve access token via POST and read streaming data using the token")
    @Story("Stream API with Token Auth")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test retrieves an access token from SEMP and uses it to initiate a streaming API call, reading for 15 seconds.")
    public void retrieveTokenAndReadStream() throws InterruptedException {

        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("clientId", "MecNecILet@Met001");
        placeholders.put("password", "d2nEH8G4dj");
        placeholders.put("userId", "363538");

        String reqBody = JsonReaderNew.loadAndReplaceJsonPlaceholders("src/test/resources/data/SempsarcBody.json",
                placeholders);

        Response tokenResponse = ApiUtils.postReqWithRawJson(SEMP_BASE_URL, ENDPOINT_URL, reqBody);

        String accessToken = tokenResponse.jsonPath().getString("data.accessToken");

        System.out.println("Access Token: " + accessToken);

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("token", accessToken);

        Response streamResponse = ApiUtils.getStreamingResponse(SEMP_GET_BASE_URL, SEMP_GET_END_URL, queryParams);

        InputStream stream = streamResponse.asInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        System.out.println("Reading stream for 15 seconds...");

        long start = System.currentTimeMillis();
        long duration = 15000; // 15 seconds

        // Create a latch to ensure we wait for threads to complete
        CountDownLatch latch = new CountDownLatch(2);

        Thread streamReaderThread = new Thread(() -> {
            try {
                String line;
                while ((System.currentTimeMillis() - start) < duration && (line = reader.readLine()) != null) {
                    System.out.println("Stream: " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });

        Thread testResultThread = new Thread(() -> {
            try {
                Thread.sleep(duration);
                System.out.println("Stream reading completed after 15 seconds");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        });

        streamReaderThread.start();
        testResultThread.start();

        // Wait for both threads to complete before ending the test
        latch.await(duration + 2000, TimeUnit.MILLISECONDS); // Wait with a small buffer

    }
}