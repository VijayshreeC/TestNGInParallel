package com.globomantics.test.testcases;

import java.util.concurrent.TimeoutException;
import java.util.jar.Attributes.Name;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.generic.utilities.Logg;
import com.generic.utilities.Utilities;
import com.globomantics.test.base.Browser;
import com.globomantics.test.base.TestBase;
import com.globomantics.test.pages.HomePage;

import com.globomantics.test.utilities.TestUtil;

public class HomePageTest extends TestBase {
	public HomePageTest() {
		// TODO Auto-generated constructor stub
		super();
	}

	HomePage homePage; 
	private static final Logger LOGGER = Logg.createLogger();

	@BeforeClass
	public void openBrowser() {
		initialization();
		homePage = new HomePage();

	}

	@Test(priority = 1)
	public void HomePageTitle() {
		LOGGER.info(Utilities.getCurrentThreadId() + "HomePage title is- " + homePage.validateHomPageTitle());
		String title = homePage.validateHomPageTitle();
		Assert.assertEquals(title, "globomantics");
	}

	@Test(priority = 2)
	public void gbmLogo() {
		LOGGER.info(Utilities.getCurrentThreadId() + "Validating Logo- " + homePage.visibilityOfLogo());
		boolean flag = homePage.visibilityOfLogo();
		Assert.assertTrue(flag);
	}

	@Test(priority = 3)
	public void gbmEmailIcon() {
		LOGGER.info(Utilities.getCurrentThreadId() + "Validating if the email icon is present or not- "
				+ homePage.visibilityOfEmailIcon());
		boolean flag = homePage.visibilityOfEmailIcon();
		Assert.assertTrue(flag);
	}

	@Test(priority = 4)
	public void gbmHomePageImage() {
		LOGGER.info(Utilities.getCurrentThreadId() + "Validating if the homepage image is present or not- "
				+ homePage.visibilityOfLogo());
		boolean flag = homePage.visibilityOfHomePageImage();
		Assert.assertTrue(flag);
	}

	@Test(priority = 5)
	public void enterDetails() {
		LOGGER.info(Utilities.getCurrentThreadId() + "Enter details: " + '\n' + (prop.getProperty("name") + '\n'
				+ prop.getProperty("emailadrs") + '\n' + prop.getProperty("remarks")));
		homePage.register(prop.getProperty("name"), prop.getProperty("emailadrs"), prop.getProperty("remarks"));

	}

	@AfterClass
	public void quitBrowser() throws Exception {
		LOGGER.info(Utilities.getCurrentThreadId() + "Closing Browser- " + prop.getProperty("browser") + " browser");
		driver.quit();
	}

}
