package com.qa.Todo.acceptance;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.Todo.pages.CreateAList;
import com.qa.Todo.pages.Homepage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;



public class SeleniumTests {
	
	private static ExtentReports extent;
	private static ExtentTest test;
	private static RemoteWebDriver driver;
	private static Logger LOGGER = Logger.getGlobal();
	
	@BeforeAll
	public static void init() {
		extent = new ExtentReports("src/test/resources/reports/report1.html", true);
		test = extent.startTest("ExtentDemo");
		System.setProperty("webdriver.gecko.driver",
				"src/test/resources/webdriver/geckodriver.exe");
		FirefoxOptions fOptions = new FirefoxOptions();
		fOptions.setHeadless(false);
		driver = new FirefoxDriver(fOptions);
		fOptions.addPreference("profile.default_content_setting_values.cookies", 2);
		fOptions.addPreference("network.cookie.cookieBehavior", 2);
		fOptions.addPreference("profile.block_third_party_cookies", true);
		driver.manage().window().setSize(new Dimension(1366, 768));

	}
	
	@BeforeEach
	public void setup() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		try {
			driver.get(Homepage.URL);
		} catch (TimeoutException e) {
			System.out.println("Page: " + Homepage.URL + " did not load within 30 seconds!");
		}
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
		extent.endTest(test);
		extent.flush();
		extent.close();

	}
	
	@Test
	public static void createAListTest() {
		LOGGER.warning("Connecting to my not so amazing todo app....");
		
		String name = "Morning";
		
		Homepage nav = PageFactory.initElements(driver, Homepage.class);
		
		CreateAList list = PageFactory.initElements(driver, CreateAList.class);
		
		nav.createListLink();
		
		list.createUser(name);
		
		list.home();
		
		assertEquals(name, driver.getPageSource().contains(name));
		
	}
}
