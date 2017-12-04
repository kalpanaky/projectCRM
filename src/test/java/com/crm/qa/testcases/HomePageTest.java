package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utility.TestUtil;

// press ctrl+shit+O  for importing a package

public class HomePageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	public HomePageTest()
	{
		super();
	}
	
	/*************************testcases should be independent of each others so for every
	 * test case browser will be opened, login,  delete cookies and close 
	 * to avoid ambiguities otherwise browser will be exhausted if we execute all the 
	 * testcases on the same browser thats why @BeforeMethod is used so for 1000 testcases 
	 * browser will be opened and close 100 times ***********************************/
	
	// test cases should be independent of each other
	
	//
	
	@BeforeMethod
	public void setUp()
	{
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		//homePage = new HomePage();
	}
	
/*	assertEquals(String actual, String expected, String "this will be only printed if testcases will be fail")
    Asserts that two Strings are equal.
*/	
	
	@Test(priority=1)
	public void verifyHomepageTitleTest()
	{
		String titleHomePage = homePage.verifyHomePageTitle();
		Assert.assertEquals(titleHomePage, "CRMPRO","Home page title did not match");
	}
	
	@Test(priority=2)
	public void verifyUserNameTest()
	{
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyUserName(),"not expected");
	}
	
	@Test (priority=3)
	public void verifyContactsLinkTest()
	{
		testUtil.switchToFrame();
		contactsPage =homePage.clickOnContactslink();
	}
	
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
