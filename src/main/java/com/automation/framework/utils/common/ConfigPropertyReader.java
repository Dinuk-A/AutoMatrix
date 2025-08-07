package com.automation.framework.utils.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//to read the config.properties file and extract the values and urls  ✅
public class ConfigPropertyReader {

    private static Properties props = new Properties();

    static {
        try {

            InputStream input = ConfigPropertyReader.class.getClassLoader().getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("config.properties file not found in classpath!");
            }

            props.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }
}

