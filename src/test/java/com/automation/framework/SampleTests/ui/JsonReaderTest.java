package com.automation.framework.SampleTests.ui;

import org.testng.annotations.Test;

import com.automation.framework.utils.common.JsonReader;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class JsonReaderTest {

    @Story("Read JSON values from a file")
    @Epic("JSON File Handling")
    @Feature("Fetching Values")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test fetches and prints the values of 'username', 'password', 'age', and 'isAdmin' from the user.json file.")
    @Test(description = "Retrieve and print JSON values from the user.json file")
    public void testFetchJsonValueByKey() {
        // Update the file path to match your actual test data location
        String filePath = "src/test/resources/data/ui/user.json";

        // Fetch and print the values using the fetchJsonValueByKey method from
        // JsonReader
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