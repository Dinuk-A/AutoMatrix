package com.automation.framework.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//to read the config.properties file and extract the base url and endpoints ✅
public class ConfigReader {

    // ORIGINAL
    // private static Properties props = new Properties();

    // ORIGINAL
    // static{
    // try {
    // System.out.println("Loading config.properties...");
    // props.load(new FileInputStream("src/test/resources/config.properties"));
    // } catch (IOException e) {
    // throw new RuntimeException("Failed to load config file");
    // }
    // }

    // ORIGINAL
    // public static String getProperty(String key) {
    // return props.getProperty(key);
    // }

    private static final Properties props = new Properties();

    static {
        try {
            System.out.println("🔍 Trying to load config.properties from classpath...");

            ClassLoader classLoader = ConfigReader.class.getClassLoader();
            InputStream input = classLoader.getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("❌ config.properties file not found in classpath!");
            }

            props.load(input);
            System.out.println("✅ config.properties loaded successfully.");

        } catch (Exception e) {
            System.err.println("🚨 Failed to load config.properties: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }

}
