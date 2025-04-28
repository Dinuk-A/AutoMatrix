package com.automation.framework.utils.api;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
     public static String createJsonFromMap(Map<String, Object> dataMap) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(dataMap);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create JSON from map", e);
        }
    }
}
