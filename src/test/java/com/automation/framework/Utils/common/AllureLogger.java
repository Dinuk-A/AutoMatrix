package com.automation.framework.Utils.common;

import io.qameta.allure.Attachment;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import java.util.Map;

public class AllureLogger {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Attaches a formatted request log to Allure reports
    @Attachment(value = "{attachmentName}", type = "text/plain")
    public static String logRequest(String attachmentName, String url, String method,
            Map<String, String> headers, String body) {

        System.out.println("logRequest Logging to Allure");
        StringBuilder requestDetails = new StringBuilder();
        requestDetails.append("=== API REQUEST ===\n");
        requestDetails.append("URL: ").append(url).append("\n");
        requestDetails.append("Method: ").append(method).append("\n");

        if (headers != null && !headers.isEmpty()) {
            requestDetails.append("\nHeaders:\n");
            headers.forEach(
                    (key, value) -> requestDetails.append("  ").append(key).append(": ").append(value).append("\n"));
        }

        if (body != null && !body.isEmpty()) {
            requestDetails.append("\nRequest Body:\n").append(formatJson(body));
        }

        return requestDetails.toString();
    }

    // Attaches a formatted request log with query parameters to Allure reports
    @Attachment(value = "{attachmentName}", type = "text/plain")
    public static String logRequestWithParams(String attachmentName, String url, String method,
            Map<String, String> headers, Map<String, String> queryParams) {
        System.out.println(" logRequestWithParams Logging to Allure");
        StringBuilder requestDetails = new StringBuilder();
        requestDetails.append("=== API REQUEST ===\n");
        requestDetails.append("URL: ").append(url).append("\n");
        requestDetails.append("Method: ").append(method).append("\n");

        if (headers != null && !headers.isEmpty()) {
            requestDetails.append("\nHeaders:\n");
            headers.forEach(
                    (key, value) -> requestDetails.append("  ").append(key).append(": ").append(value).append("\n"));
        }

        if (queryParams != null && !queryParams.isEmpty()) {
            requestDetails.append("\nQuery Parameters:\n");
            queryParams.forEach(
                    (key, value) -> requestDetails.append("  ").append(key).append("=").append(value).append("\n"));
        }

        return requestDetails.toString();
    }

    // Attaches a formatted response log to Allure reports
    @Attachment(value = "{attachmentName}", type = "text/plain")
    public static String logResponse(String attachmentName, Response response) {
        System.out.println("logResponse Logging to Allure");
        StringBuilder responseDetails = new StringBuilder();
        responseDetails.append("=== API RESPONSE ===\n");
        responseDetails.append("Status Code: ").append(response.getStatusCode()).append("\n");
        responseDetails.append("Response Time: ").append(response.getTime()).append(" ms\n");

        // Response Headers
        responseDetails.append("\nResponse Headers:\n");
        response.getHeaders().forEach(header -> responseDetails.append("  ").append(header.getName()).append(": ")
                .append(header.getValue()).append("\n"));

        // Response Body
        String responseBody = response.getBody().asString();
        if (responseBody != null && !responseBody.isEmpty()) {
            responseDetails.append("\nResponse Body:\n").append(formatJson(responseBody));
        }

        return responseDetails.toString();
    }

    // Attaches a formatted request and response log to Allure reports
    @Attachment(value = "{attachmentName}", type = "text/plain")
    public static String logRequestAndResponse(String attachmentName, String url, String method,
            Map<String, String> headers, String requestBody,
            Response response) {
        System.out.println("logRequestAndResponse Logging to Allure");
        StringBuilder details = new StringBuilder();

        // Request section
        details.append("=== API REQUEST ===\n");
        details.append("URL: ").append(url).append("\n");
        details.append("Method: ").append(method).append("\n");

        if (headers != null && !headers.isEmpty()) {
            details.append("\nRequest Headers:\n");
            headers.forEach((key, value) -> details.append("  ").append(key).append(": ").append(value).append("\n"));
        }

        if (requestBody != null && !requestBody.isEmpty()) {
            details.append("\nRequest Body:\n").append(formatJson(requestBody));
        }

        // Response section
        details.append("\n\n=== API RESPONSE ===\n");
        details.append("Status Code: ").append(response.getStatusCode()).append("\n");
        details.append("Response Time: ").append(response.getTime()).append(" ms\n");

        details.append("\nResponse Headers:\n");
        response.getHeaders().forEach(header -> details.append("  ").append(header.getName()).append(": ")
                .append(header.getValue()).append("\n"));

        String responseBody = response.getBody().asString();
        if (responseBody != null && !responseBody.isEmpty()) {
            details.append("\nResponse Body:\n").append(formatJson(responseBody));
        }

        return details.toString();
    }

    // Attaches a formatted JSON response log to Allure reports
    @Attachment(value = "{attachmentName}", type = "application/json")
    public static String logJsonResponse(String attachmentName, Response response) {
        System.out.println("logJsonResponse Logging to Allure");
        String responseBody = response.getBody().asString();
        return formatJson(responseBody);
    }

    // Attaches a plain text log to Allure reports
    @Attachment(value = "{attachmentName}", type = "text/plain")
    public static String logText(String attachmentName, String content) {
        System.out.println("Logging to Allure");
        return content;
    }

    // Formats a JSON string for better readability
    private static String formatJson(String json) {
        System.out.println("Logging to Allure");
        try {
            Object jsonObject = objectMapper.readValue(json, Object.class);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        } catch (Exception e) {
            // If JSON formatting fails, return original string with error note
            return json + "\n\n[Note: Could not format as JSON - " + e.getMessage() + "]";
        }
    }

    // Attaches error details to Allure reports
    @Attachment(value = "{attachmentName}", type = "text/plain")
    public static String logError(String attachmentName, Throwable error) {
        System.out.println( " logErrorL ogging to Allure");
        StringBuilder errorDetails = new StringBuilder();
        errorDetails.append("=== ERROR DETAILS ===\n");
        errorDetails.append("Error Type: ").append(error.getClass().getSimpleName()).append("\n");
        errorDetails.append("Error Message: ").append(error.getMessage()).append("\n");
        errorDetails.append("\nStack Trace:\n");

        for (StackTraceElement element : error.getStackTrace()) {
            errorDetails.append("  at ").append(element.toString()).append("\n");
        }

        return errorDetails.toString();
    }

}
