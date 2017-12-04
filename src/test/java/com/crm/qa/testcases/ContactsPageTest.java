package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utility.TestUtil;

public class ContactsPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "sheet1";
	
	public ContactsPageTest()
	{
		super();
	}
	
	/*************************test cases should be independent of each others so for every
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
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactslink();
		//homePage = new HomePage();
	}
	
	@Test (priority=1)
	public void verifyContactsPageLable()
	{
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "Contacts label is missing on the page");
	}
	
	@Test(priority=2)
	public void selectSingleContactsTest()
	{
		contactsPage.selectContactsByName("aab nnn");
	}
	
	@Test(priority=3)
	public void selectMultipleContactsTest()
	{
		contactsPage.selectContactsByName("aab nnn");
		contactsPage.selectContactsByName("john due");
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] data = TestUtil.getTestData(sheetName);
		
		return data;
	}
	
	
	
	@Test(priority  = 4, dataProvider = "getData")
	public void validateCreateNewContact(String title,String FirstName, String LastName,String company)
	{
		homePage.clickOnNeContactLink();
		contactsPage.createNewContacts(title, FirstName, LastName, company);
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
