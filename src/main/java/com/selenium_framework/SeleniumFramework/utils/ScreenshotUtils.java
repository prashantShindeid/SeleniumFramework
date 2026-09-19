package com.selenium_framework.SeleniumFramework.utils;



//import com.selenium_framework.SeleniumFramework.config.ConfigReader;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);

    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = testName + "_" + timestamp + ".png";
        String destPath = ConfigReader.getInstance().getScreenshotPath() + fileName;

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(destPath);
            FileUtils.copyFile(srcFile, destFile);
            logger.info("Screenshot captured: {}", destPath);
            return destPath;
        } catch (IOException e) {
            logger.error("Failed to capture screenshot for test: {}", testName, e);
            return null;
        }
    }
}