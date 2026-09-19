package com.selenium_framework.SeleniumFramework.base;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.selenium_framework.SeleniumFramework.config.ConfigReader;

public abstract class BaseTest {

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriver driver = DriverManager.getDriver();
        driver.get(ConfigReader.getInstance().getBaseUrl());
        logger.info("Browser launched and navigated to base URL");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
        logger.info("Browser closed");
    }

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}