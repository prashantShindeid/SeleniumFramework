package com.selenium_framework.SeleniumFramework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.selenium_framework.SeleniumFramework.base.BasePage;

public class AddToCart extends BasePage{

	AddToCart(WebDriver driver){
	    super(driver);
	    PageFactory.initElements(driver, this);
	}
	
	public String getProductName(String expectedName) {
	    String actualText = driver.findElement(By.xpath("//h3[text()='"+expectedName+"']")).getText();
		return actualText;
	}
}
