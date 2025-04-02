package com.automation.framework.api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//to read the config.properties file and extract the base url and endpoints ✅
public class ConfigReader {

    private static Properties props = new Properties();

    // to load the file
    static{
        try {
            props.load(new FileInputStream("src/test/java/com/automation/framework/config/config.properties"));
        } catch (IOException  e) {
           throw new RuntimeException("Failed to load config file");
        }
    }

    // to extract the needed data
    public static String getProperty(String key) {
        return props.getProperty(key);
    }

}
