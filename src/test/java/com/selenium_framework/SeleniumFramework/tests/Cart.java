package com.selenium_framework.SeleniumFramework.tests;


import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.selenium_framework.SeleniumFramework.base.BaseTest;
import com.selenium_framework.SeleniumFramework.pages.Dashboard;
import com.selenium_framework.SeleniumFramework.pages.LoginPage;
import com.selenium_framework.SeleniumFramework.utils.ExcelDataProvider;
import com.selenium_framework.SeleniumFramework.utils.ExcelSource;


public class Cart extends BaseTest{
	
 
	@Test(dataProvider= "excelData", dataProviderClass = ExcelDataProvider.class)
	@ExcelSource(file = "Login.xlsx",sheet="login")
	@ExcelSource(file="Cart.xlsx",sheet="cart")
	public void verifyAddToCart(Map<String,String> row) {
		WebDriver driver = getDriver();
	    Dashboard dashboard = new Dashboard(driver);
	    LoginPage loginPage=new LoginPage(driver);
		loginPage.login(row.get("username"), row.get("password"));
		dashboard.AddToCart(row.get("ProductName"));
		dashboard.clickCartIcon();
	
	}
}
