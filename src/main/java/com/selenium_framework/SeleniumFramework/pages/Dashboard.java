package com.selenium_framework.SeleniumFramework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium_framework.SeleniumFramework.base.BasePage;

public class Dashboard extends BasePage{

	
	private @FindBy(css = "button[routerlink='/dashboard/cart']")
	WebElement cart;
	
	
	public Dashboard(WebDriver driver){
		 super(driver);
    	 PageFactory.initElements(driver, this);
	}
	
	public void clickCartIcon() {
	    safeClick(cart);
	}
	
	public void AddToCart(String productName) {
		//ADIDAS ORIGINAL
	   WebElement product=driver.findElement(By.xpath("//h5/b[text()='"+productName+"']/parent::h5/following-sibling::button[2]"));	
	   waitForClickable(product);
	   safeClick(product);
	}
}
