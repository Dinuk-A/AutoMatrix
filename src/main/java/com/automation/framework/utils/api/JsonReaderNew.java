package com.automation.framework.utils.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.io.IOException;

public class JsonReaderNew {

    // for test sempsarc
    public static String loadAndReplaceJsonPlaceholders(String filePath, Map<String, String> placeholders) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));

            for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                json = json.replace("{{" + entry.getKey() + "}}", entry.getValue());
            }

            return json;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }

}
