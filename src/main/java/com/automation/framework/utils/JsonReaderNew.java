package com.automation.framework.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class JsonReaderNew {
    
    // Reads a JSON file and replaces placeholders with actual values
    public static String readJsonFile(String filePath, String name, String job) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            json = json.replace("{{name}}", name)
                    .replace("{{job}}", job);
            return json;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }
}
