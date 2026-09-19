package com.selenium_framework.SeleniumFramework.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium_framework.SeleniumFramework.base.BasePage;

public class LoginPage extends BasePage {

	   @FindBy(id="userEmail")
	   WebElement emailField;
	
	   @FindBy(id="userPassword")
	   WebElement passwordField;
	   
	   @FindBy(id ="login")
	   WebElement loginButton;
	    	         
     
     public LoginPage(WebDriver driver) {
    	 super(driver);
		 // Default constructor
    	 PageFactory.initElements(driver, this);
	 }
      
     public void login(String email, String password) {
         safeType(emailField, email);
         safeType(passwordField, password);
         safeClick(loginButton);
     }
}