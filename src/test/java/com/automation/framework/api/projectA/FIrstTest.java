package com.automation.framework.api.projectA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

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

//Remove-Item -Recurse -Force .\allure-results; mvn test -Dtest=FIrstTest
//mvn test -Dtest=FIrstTest
public class FIrstTest {

    // to save and use the access token later
    // mehema karanna wenne or mekatama class ekak hadana eka 💥💥💥💥💥
    private static String accessToken;

    private static final String SEMP_BASE_URI = ConfigReader.getProperty("sempsarc.base.url");

    private static final String ENDPOINT_URL = ConfigReader.getProperty("sempsarc.post.url");

    private static final String SEMP_GET_BASE_URL = ConfigReader.getProperty("sempsarc.get.base.url");

    private static final String SEMP_GET_END_URL = ConfigReader.getProperty("sempsarc.get.endpoint");

    /// mvn clean

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
    @Test(invocationTimeOut = 35000)
    public void sempsarcGetStreamWithTimeout() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("token", accessToken);
    
        io.restassured.response.Response response = null;
        BufferedReader reader = null;
        InputStream inputStream = null;
    
        // ✅ Add this
        ExecutorService executor = Executors.newSingleThreadExecutor();
        AtomicInteger lineCount = new AtomicInteger(0);
    
        try {
            response = ApiUtils.getReqWithQueryParamsForSempSarc(SEMP_GET_BASE_URL, SEMP_GET_END_URL, queryParams);
            AssertionUtils.assertStatusCode(response, HttpStatusCode.OK.getCode());
    
            inputStream = response.asInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
    
            final BufferedReader readerFinal = reader; // use final inside lambda
    
            Future<Integer> future = executor.submit(() -> {
                int count = 0;
                try {
                    String line;
                    while ((line = readerFinal.readLine()) != null) {
                        if (!line.trim().isEmpty()) {
                            System.out.println("Data: " + line);
                            count++;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Stream reading interrupted: " + e.getMessage());
                }
                return count;
            });
    
            try {
                int count = future.get(20, TimeUnit.SECONDS);
                lineCount.set(count);
            } catch (TimeoutException e) {
                System.out.println("Time limit reached. Stopping stream.");
            } catch (Exception e) {
                System.out.println("Error reading stream: " + e.getMessage());
            } finally {
                future.cancel(true);
                executor.shutdownNow();
            }
    
            System.out.println("Stream read completed with " + lineCount.get() + " lines.");
            Assert.assertTrue(lineCount.get() > 0, "No streaming data was received.");
    
        } catch (Exception e) {
            Assert.fail("Test failed: " + e.getMessage());
        } finally {
            try {
                if (reader != null)
                    reader.close();
                if (inputStream != null)
                    inputStream.close();
    
                if (response != null && response.getBody() != null) {
                    response.getBody().asInputStream().close();
                }
            } catch (Exception e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    
}

// mvn test -Dtest=FIrstTest