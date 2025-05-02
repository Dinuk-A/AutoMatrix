package com.automation.framework.utils.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//to read the config.properties file and extract the base url and endpoints ✅
public class ConfigPropertyReader  {

    private static Properties props = new Properties();

    static {
        try {

            System.out.println("Loading config.properties...");

            InputStream input = ConfigPropertyReader .class.getClassLoader().getResourceAsStream("config.properties");

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
