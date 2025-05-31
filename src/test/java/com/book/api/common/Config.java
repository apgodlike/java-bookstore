package com.book.api.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();
    
    static {
        try {
            InputStream configStream = Config.class.getClassLoader().getResourceAsStream("config.properties");
            if (configStream != null) {
                props.load(configStream);
                configStream.close();
                System.out.println("Successfully loaded config.properties");
            } else {
                System.err.println("config.properties file not found in classpath");
            }
        } catch (IOException e) {
            System.err.println("Could not load config.properties: " + e.getMessage());
        }
    }
    
    public static String get(String key) {
        String envKey = key.toUpperCase().replace('.', '_');
        String envValue = System.getenv(envKey);
        
        if (envValue != null && !envValue.trim().isEmpty()) {
            System.out.println("Found environment variable " + envKey + " = " + envValue);
            return envValue;
        }
        
        envValue = System.getenv(key);
        if (envValue != null && !envValue.trim().isEmpty()) {
            System.out.println("Found environment variable " + key + " = " + envValue);
            return envValue;
        }
        
        String propValue = props.getProperty(key);
        if (propValue != null) {
            System.out.println("Found property " + key + " = " + propValue);
            return propValue;
        }
        
        System.err.println("Could not find value for key: " + key + " (tried env vars: " + envKey + ", " + key + ", and properties file)");
        return null;
    }
    
}