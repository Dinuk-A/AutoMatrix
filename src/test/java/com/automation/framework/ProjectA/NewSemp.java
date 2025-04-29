package com.automation.framework.ProjectA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.automation.framework.utils.api.ApiUtils;
import com.automation.framework.utils.api.JsonReaderNew;
import com.automation.framework.utils.common.ConfigReader;

import io.restassured.response.Response;

public class NewSemp {

    private static final String SEMP_BASE_URL = ConfigReader.getProperty("sempsarc.base.url");
    private static final String ENDPOINT_URL = ConfigReader.getProperty("sempsarc.post.url");
    private static final String SEMP_GET_BASE_URL = ConfigReader.getProperty("sempsarc.get.base.url");
    private static final String SEMP_GET_END_URL = ConfigReader.getProperty("sempsarc.get.endpoint");

    @Test
    public void retrieveTokenAndReadStream() {

        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("clientId", "MecNecILet@Met001");
        placeholders.put("password", "d2nEH8G4dj");
        placeholders.put("userId", "363538");

        String reqBody = JsonReaderNew.loadAndReplaceJsonPlaceholders("src/test/resources/data/SempsarcBody.json", placeholders);

        Response tokenResponse =ApiUtils.postReqWithRawJson(SEMP_BASE_URL, ENDPOINT_URL, reqBody);

        String accessToken = tokenResponse.jsonPath().getString("data.accessToken");

        System.out.println("Access Token: " + accessToken);

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("token", accessToken);

        Response streamResponse = ApiUtils.getStreamingResponse(SEMP_GET_BASE_URL, SEMP_GET_END_URL, queryParams);

         InputStream stream = streamResponse.asInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        System.out.println("Reading stream for 5 seconds...");

        long start = System.currentTimeMillis();
        long duration = 15000; // 15 seconds

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
            }
        });

        Thread testResultThread = new Thread(() -> {
            try {
                Thread.sleep(duration);
                System.out.println("Stream closed ✅");
                // Add your code to display Maven test results here
                // For example:
                // Remove-Item -Recurse -Force .\allure-results; mvn test -Dtest=Third
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        streamReaderThread.start();
        testResultThread.start();


    }

}
