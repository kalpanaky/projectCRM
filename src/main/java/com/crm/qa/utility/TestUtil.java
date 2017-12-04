package com.crm.qa.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static String SHEET_PATH= "C:/STSworkspace/Practicing/src/main/java/com/crm/qa/testdata/freeCRM_Contacts.xlsx";

	static Sheet sheet;
	static Workbook book;
	public void switchToFrame()
	{

		driver.switchTo().frame("mainpanel");
	}
	public 	static Object[][] getTestData(String sheetName)
	{
		FileInputStream file = null;
		try {
			file = new FileInputStream(SHEET_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);

		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for(int i=0;i<sheet.getLastRowNum();i++)
		{
			for(int k=0; k<sheet.getRow(0).getLastCellNum();k++)
			{
				data[i][k]= sheet.getRow(i+1).getCell(k).toString();

			}  

		}

		return data;

	}
	public static void takeScreenshotAtEndOfTest() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String currentDir = System.getProperty("user.dir");
		
		/*******************The java.lang.System.currentTimeMillis() method returns the 
		 * current time in milliseconds.The unit of time of the return value is a millisecond, the 
		 * granularity of the value depends on the underlying operating system and may be larger.*****/

		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));

		

		}
}

