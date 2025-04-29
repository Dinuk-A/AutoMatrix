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
import io.qameta.allure.*;

@Epic("SEMP Streaming API")
@Feature("Token Retrieval and Stream Reading")
public class New {

    private static final String SEMP_BASE_URL = ConfigReader.getProperty("sempsarc.base.url");
    private static final String ENDPOINT_URL = ConfigReader.getProperty("sempsarc.post.url");
    private static final String SEMP_GET_BASE_URL = ConfigReader.getProperty("sempsarc.get.base.url");
    private static final String SEMP_GET_END_URL = ConfigReader.getProperty("sempsarc.get.endpoint");

    // Class variable to store the token between test methods
    private static String accessToken;

    @Test(description = "Retrieve access token via POST", priority = 1)
    @Story("Token Retrieval")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test retrieves an access token from SEMP")
    public void retrieveToken() {
        System.out.println("Loading config.properties...");

        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("clientId", "MecNecILet@Met001");
        placeholders.put("password", "d2nEH8G4dj");
        placeholders.put("userId", "363538");

        String reqBody = JsonReaderNew.loadAndReplaceJsonPlaceholders("src/test/resources/data/SempsarcBody.json",
                placeholders);

        Response tokenResponse = ApiUtils.postReqWithRawJson(SEMP_BASE_URL, ENDPOINT_URL, reqBody);

        accessToken = tokenResponse.jsonPath().getString("data.accessToken");

        System.out.println("Access Token: " + accessToken);
    }

    @Test(description = "Read streaming data using the token", priority = 2, dependsOnMethods = { "retrieveToken" })
    @Story("Stream API with Token Auth")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test uses the access token to initiate a streaming API call, reading for 15 seconds.")
    public void readStream() throws InterruptedException {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("token", accessToken);

        Response streamResponse = ApiUtils.getStreamingResponse(SEMP_GET_BASE_URL, SEMP_GET_END_URL, queryParams);

        InputStream stream = streamResponse.asInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        System.out.println("Reading stream for 15 seconds...");

        long startTime = System.currentTimeMillis();
        long duration = 15000; // 15 seconds

        try {
            String line;
            while ((System.currentTimeMillis() - startTime) < duration && (line = reader.readLine()) != null) {
                System.out.println("Stream: " + line);
            }
            // Sleep for any remaining time to ensure we read for the full duration
            long elapsed = System.currentTimeMillis() - startTime;
            if (elapsed < duration) {
                Thread.sleep(duration - elapsed);
            }
            System.out.println("Stream reading completed after 15 seconds");
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

    }
}
