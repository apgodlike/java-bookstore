package com.book.api.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        try {
        	props.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            System.err.println("Could not load config.properties");
        }
    }

    public static String get(String key) {
        String envValue = System.getenv(key.toUpperCase().replace('.', '_'));
        if (envValue != null) return envValue;

        return props.getProperty(key);
    }
}
