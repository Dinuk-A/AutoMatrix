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

    private static Properties props = new Properties();

    static {
        try {
            // Debugging: print the current working directory
            System.out.println("Classpath location: " + System.getProperty("user.dir"));

            System.out.println("Loading config.properties...");
            // Make sure to load from classpath
            InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("❌ config.properties file not found in classpath!");
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
