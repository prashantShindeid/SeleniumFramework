package com.selenium_framework.SeleniumFramework.tests;

import java.util.Map;

import org.testng.annotations.Test;

import com.selenium_framework.SeleniumFramework.base.BaseTest;
import com.selenium_framework.SeleniumFramework.pages.LoginPage;
import com.selenium_framework.SeleniumFramework.utils.ExcelDataProvider;
import com.selenium_framework.SeleniumFramework.utils.ExcelSource;


public class Login extends BaseTest {
	   

	    @Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	    @ExcelSource(file = "Login.xlsx",sheet="login")
	    public void testlogin(Map<String, String> row) {
	    	LoginPage loginPage = new LoginPage(getDriver()); 
	        loginPage.login(row.get("username"), row.get("password"));
//	        Assert.assertTrue(loginPage.isDashboardLoaded(),
//	                "Login did not redirect to dashboard for user: " + row.get("username"));
	    }
}