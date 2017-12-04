package com.crm.qa.base;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.utility.WebEventListener;

//import com.crm.qa.utility.TestUtil;

// Parent Class for all classes

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;

	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	public TestBase() {
		prop = new Properties();

		try {
			FileInputStream fis = new FileInputStream(
					"C:/STSworkspace/Practicing/src/main/java/com/crm/qa/config/config.properties");
			prop.load(fis);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver", "c:/updateddrivers/chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equals("FF")) {

			System.setProperty("webdriver.geko.driver", "path to geko EXE");
			driver = new FirefoxDriver();

		}

		/***********************************
		 * Important code
		 *****************************/
		/************************************* MEMORIZE *******************************/

		e_driver = new EventFiringWebDriver(driver);

		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver

		eventListener = new WebEventListener();

		e_driver.register(eventListener);

		driver = e_driver;

		/************************************* MEMORIZE *******************************/
		/*************************************
		 * Important code
		 ***************************/

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

	}

}
