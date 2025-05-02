package com.automation.framework.SampleTests.ui;

import org.testng.annotations.Test;

import com.automation.framework.utils.common.JsonReader;

public class JsonReaderTest {

    @Test
    public void testFetchJsonValueByKey() {
        // Update the file path to match your actual test data location
        String filePath = "src/test/resources/data/ui/user.json";

        // Fetch and print the values using the fetchJsonValueByKey method from JsonReader
        Object username = JsonReader.fetchJsonValueByKey(filePath, "username");
        Object password = JsonReader.fetchJsonValueByKey(filePath, "password");
        Object age = JsonReader.fetchJsonValueByKey(filePath, "age");
        Object isAdmin = JsonReader.fetchJsonValueByKey(filePath, "isAdmin");

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Age: " + age);
        System.out.println("Is Admin: " + isAdmin);
    }
}