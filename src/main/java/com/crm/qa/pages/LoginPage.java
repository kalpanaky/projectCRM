package com.crm.qa.pages;

import com.crm.qa.base.TestBase;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {

	
	//Page Factory   OR  Object Repository OR: not writing the test cases but 
	// Defining the page libraries
	
	/*************deifining web objects/ elements of LoginPage *****************/
	
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath= "//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath= "//button[contains(text(),'Sign Up')]")
	WebElement signUpBtn;
	
	@FindBy(xpath="//img[contains(@class,'img-responsive')]")
	WebElement crmLogo;
	
	// Create constructor to initialze web elements----> We use Page 
	//Factory pattern to initialize web elements which are defined in Page Objects.
	//http://www.seleniumeasy.com/selenium-tutorials/page-factory-pattern-in-selenium-webdriver
	
	/***********Initializing the page Objects**********/
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	// create Methods to validate functionality of Login Page
	
	/***********Actions---> features of login Page**********/
	
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}
	
	public boolean validateCRMImage()
	{
		return crmLogo.isDisplayed();
	}
	
	public HomePage login(String un, String pwd)
	{
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.click();
		return new HomePage();
	}

}
