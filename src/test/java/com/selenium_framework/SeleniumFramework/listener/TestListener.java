package com.selenium_framework.SeleniumFramework.listener;


import com.aventstack.extentreports.Status;
import com.selenium_framework.SeleniumFramework.base.DriverManager;
import com.selenium_framework.SeleniumFramework.utils.ExtentReportManager;
import com.selenium_framework.SeleniumFramework.utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportManager.createTest(result.getMethod().getMethodName());
        logger.info("===== STARTING TEST: {} =====", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "Test passed");
        logger.info("===== TEST PASSED: {} =====", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.error("===== TEST FAILED: {} =====", testName, result.getThrowable());

        String screenshotPath = ScreenshotUtils.captureScreenshot(DriverManager.getDriver(), testName);
        ExtentReportManager.getTest().log(Status.FAIL, result.getThrowable());
        if (screenshotPath != null) {
            try {
                ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                logger.warn("Could not attach screenshot to report", e);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().log(Status.SKIP, "Test skipped: " + result.getThrowable());
        logger.warn("===== TEST SKIPPED: {} =====", result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flush();
    }
}
