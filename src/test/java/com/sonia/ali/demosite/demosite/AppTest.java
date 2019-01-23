package com.sonia.ali.demosite.demosite;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AppTest {

	WebDriver driver;
	WebElement checkElement;
	WebElement myDynamicElement;
	ExtentReports extent;
	ExtentTest test;

	@Before
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		extent = new ExtentReports("C:\\Users\\Admin\\Desktop\\DemoReport.html", true);
	}

	@After
	public void teardown() throws InterruptedException {
		driver.quit();

	}

	@Test
	public void mthodTest() throws InterruptedException {
		test = extent.startTest("Verify application Title");
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.PASS, "Verify Title of the page");
		driver.manage().window().maximize();
		driver.get(Constants.websiteURL);
		BasicDemo page1 = PageFactory.initElements(driver, BasicDemo.class);
		page1.searchFor();
		AddUserPage page2 = PageFactory.initElements(driver, AddUserPage.class);
		page2.searching();
		// Thread.sleep(3000);
		LoginPage page3 = PageFactory.initElements(driver, LoginPage.class);
		page3.searching();
		// Thread.sleep(3000);

		String login = driver
				.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"))
				.getAttribute("innerHTML");

		test.log(LogStatus.INFO, "Login Successful");

		if (login.equals("**Successful Login**")) {
			test.log(LogStatus.PASS, "this passed");
		} else {
			test.log(LogStatus.FAIL, "this failed");
		}

		extent.endTest(test);
		extent.flush();

		assertEquals("Unsuccessful Login", "**Successful Login**", login);

	}
}