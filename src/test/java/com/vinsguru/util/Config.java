package com.vinsguru.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

// this class is used to override the default configuration in default.properties file if there is a change
public class Config {
    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PROPERTIES = "config/default.properties";
    private static Properties properties;

    public static void initialize() throws Exception {
        // load default properties
        properties = loadProperties();

        // check if there is any system variable passed from the command, and if there is, override
        for(String key: properties.stringPropertyNames()) {
            if(System.getProperties().containsKey(key)) {
                properties.setProperty(key, System.getProperty(key));
            }
        }

        log.info("Test Properties");
        for(String key: properties.stringPropertyNames()) {
            log.info("{}={}", key, properties.getProperty(key));
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    private static Properties loadProperties() throws Exception {
        Properties properties = new Properties();
        InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTIES);
        properties.load(stream);
        return properties;
    }
}
