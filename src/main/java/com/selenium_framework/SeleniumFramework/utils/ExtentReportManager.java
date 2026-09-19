package com.selenium_framework.SeleniumFramework.utils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String reportPath = "test-output/reports/TestReport_" + timestamp + ".html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("Automation Test Report");
            spark.config().setReportName("Selenium Automation Suite");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Framework", "Selenium + TestNG + Java");
        }
        return extent;
    }

    public static void createTest(String testName) {
        test.set(getInstance().createTest(testName));
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void flush() {
        getInstance().flush();
    }
}