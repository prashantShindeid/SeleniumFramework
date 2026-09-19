package com.selenium_framework.SeleniumFramework.listener;


import com.selenium_framework.SeleniumFramework.utils.ConfigReader;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private final int maxRetryCount = ConfigReader.getInstance().getMaxRetryCount();

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retrying test '" + result.getName() + "' - attempt " + retryCount);
            return true;
        }
        return false;
    }
}