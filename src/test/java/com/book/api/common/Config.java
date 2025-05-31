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
        // First, try to get from environment variables
        // Convert property key format (base.uri) to env var format (BASE_URI)
        String envKey = key.toUpperCase().replace('.', '_');
        String envValue = System.getenv(envKey);
        
        if (envValue != null && !envValue.trim().isEmpty()) {
            System.out.println("Found environment variable " + envKey + " = " + envValue);
            return envValue;
        }
        
        // If not found in env vars, try the original key format
        envValue = System.getenv(key);
        if (envValue != null && !envValue.trim().isEmpty()) {
            System.out.println("Found environment variable " + key + " = " + envValue);
            return envValue;
        }
        
        // Finally, fall back to properties file
        String propValue = props.getProperty(key);
        if (propValue != null) {
            System.out.println("Found property " + key + " = " + propValue);
            return propValue;
        }
        
        System.err.println("Could not find value for key: " + key + " (tried env vars: " + envKey + ", " + key + ", and properties file)");
        return null;
    }
    
    // Helper method to debug what's available
    public static void debugConfig() {
        System.out.println("=== Config Debug ===");
        System.out.println("Environment variables:");
        System.getenv().entrySet().stream()
            .filter(entry -> entry.getKey().contains("BASE") || entry.getKey().contains("PASSWORD") || 
                           entry.getKey().contains("base") || entry.getKey().contains("password"))
            .forEach(entry -> System.out.println("  " + entry.getKey() + " = " + entry.getValue()));
        
        System.out.println("Properties file values:");
        props.entrySet().forEach(entry -> System.out.println("  " + entry.getKey() + " = " + entry.getValue()));
        System.out.println("===================");
    }
}