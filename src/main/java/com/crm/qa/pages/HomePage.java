package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

// page library for home page

public class HomePage extends TestBase{
	
	@FindBy(xpath="//td[contains(text(),'User: Naveen K')]")
	WebElement userNameLabel;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contactsXpath;
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	WebElement dealsXpath;
	
	@FindBy(xpath="//a[contains(text(),'Tasks')]")
	WebElement tasksXpath;
	
	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	WebElement newContactXpath;
	
/**********************initialization *******************************************/	
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
    
	public String verifyHomePageTitle()
	{
		return driver.getTitle();
	}
	
	public boolean verifyUserName()
	{
		return userNameLabel.isDisplayed();
	}
	
	public ContactsPage clickOnContactslink()
	{
		contactsXpath.click();
		return new ContactsPage();
	}
	
	public DealsPage clickOnDealslink()
	{
		dealsXpath.click();
		return new DealsPage();
	}
	
	public TasksPage clickOnTaskslink()
	{
		tasksXpath.click();
		return new TasksPage();
	}
	
	public void clickOnNeContactLink()
	{
		Actions action = new Actions(driver);
		action.moveToElement(contactsXpath).build().perform();
		newContactXpath.click();
	}
	
	
	
}
