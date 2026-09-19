package com.selenium_framework.SeleniumFramework.base;


import com.selenium_framework.SeleniumFramework.config.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Logger logger = LogManager.getLogger(BasePage.class);
    private final By loadingSpinner = By.cssSelector(".ngx-spinner-overlay");

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInstance().getExplicitWait()));
    }

    protected WebElement waitForVisibility(WebElement ele) {
        return wait.until(ExpectedConditions.visibilityOf(ele));
    }

    protected WebElement waitForClickable(WebElement locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected List<WebElement> waitForAllVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    
    protected void waitForSpinnerToDisappear() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));
        } catch (Exception ignored) {
            // Spinner kabhi dikha hi nahi to koi dikkat nahi — bas aage badho
        }
    }
    
    protected void safeClick(WebElement locator) {
        waitForSpinnerToDisappear();   // ← pehle spinner hatne do
        logger.debug("Clicking element: {}", locator);
        WebElement element = waitForClickable(locator);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            // Agar phir bhi spinner ka timing race lag jaaye, JS click fallback
            logger.warn("Normal click intercepted for {}, falling back to JS click", locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    protected void safeType(WebElement ele, String text) {
        logger.debug("Typing '{}' into element: {}", text, ele);
        WebElement element = waitForVisibility(ele);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement locator) {
        return waitForVisibility(locator).getText();
    }

    protected boolean isDisplayed(WebElement locator) {
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void selectByVisibleText(WebElement locator, String visibleText) {
        Select dropdown = new Select(waitForVisibility(locator));
        dropdown.selectByVisibleText(visibleText);
    }

    protected void waitForUrlContains(String partialUrl) {
        wait.until(ExpectedConditions.urlContains(partialUrl));
    }
}
