package com.automation.framework.utils;

import org.testng.Assert;


//💥💥💥try to pass response directly inside them
public class AssertionUtils {

    // Assertion method for status codes
    public static void assertStatusCode(int actualCode, int expectedCode) {
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
    public static void assertContentType(String actualContentType, String expectedContentType) {
        Assert.assertTrue(actualContentType.contains(expectedContentType),
                "Expected Content-Type: " + expectedContentType + " but got: " + actualContentType);
    }

    // MORE NEEDED 💥💥💥
    // Checks if the response body contains the expected data.
    // Verifies the presence of specific headers and their values.
    //check if body is empty(in puts or in deletes)
    // Extracts values from a JSON response using JSON Path expressions, and
    // compares the extracted data.

    //Cookies
}
