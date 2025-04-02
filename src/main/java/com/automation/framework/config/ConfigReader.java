package com.automation.framework.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//to read the config.properties file and extract the base url and endpoints ✅
public class ConfigReader {
    
    private static Properties props = new Properties();

    // to load the file
    static{
        try {
            System.out.println("Loading config.properties...");
            props.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (IOException  e) {
           throw new RuntimeException("Failed to load config file");
        }
    }

    // to extract the needed data
    public static String getProperty(String key) {
        return props.getProperty(key);
    }
    
}
