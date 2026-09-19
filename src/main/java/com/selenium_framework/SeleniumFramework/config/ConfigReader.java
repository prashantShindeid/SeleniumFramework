package com.selenium_framework.SeleniumFramework.config;



import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static ConfigReader instance;
    private final Properties properties;

    private ConfigReader() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static synchronized ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public String get(String key) {
        String override = System.getProperty(key);
        return override != null ? override : properties.getProperty(key);
    }

    public String getBaseUrl() {
        String env = get("env");
        return get(env + ".url");
    }

    public String getBrowser() {
        return get("browser");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public int getImplicitWait() {
        return Integer.parseInt(get("implicit.wait"));
    }

    public int getExplicitWait() {
        return Integer.parseInt(get("explicit.wait"));
    }

    public int getPageLoadTimeout() {
        return Integer.parseInt(get("page.load.timeout"));
    }

    public int getMaxRetryCount() {
        return Integer.parseInt(get("max.retry.count"));
    }

    public String getScreenshotPath() {
        return get("screenshot.path");
    }
}