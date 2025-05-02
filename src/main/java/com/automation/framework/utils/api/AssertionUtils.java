package com.automation.framework.utils.api;

import org.testng.Assert;
import io.restassured.response.Response;

public class AssertionUtils {

    public static void assertStatusCode(Response response, int expectedCode) {
        int actualCode = response.getStatusCode();
        Assert.assertEquals(actualCode, expectedCode, "Status code is not as expected.");
    }

    // Assertion method for checking response body content
    public static void assertResponseBodyContains(String actualBody, String expectedText) {
        Assert.assertTrue(actualBody.contains(expectedText), "Response body does not contain the expected text.");
    }

    // Assertion method for response time
    public static void assertResponseTime(long responseTime, long maxTime) {
        Assert.assertTrue(responseTime < maxTime,
                "Response time exceeded the maximum allowed time. Actual time: " + responseTime + "ms");
    }

    // Assertion method for checking if the response body is empty
    // this is particularly useful for DELETE or HEAD requests
    public static void assertResponseBodyIsEmpty(String actualBody) {
        Assert.assertTrue(actualBody.isEmpty(), "Response body is not empty.");
    }

    // Assertion method for checking content type
    public static void assertContentType(Response response, String expectedContentType) {
        String actualContentType = response.getHeader("Content-Type");
        Assert.assertTrue(actualContentType.contains(expectedContentType),
                "Expected Content-Type: " + expectedContentType + " but got: " + actualContentType);
    }

    // MORE NEEDED 💥💥💥

}
