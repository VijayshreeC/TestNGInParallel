package com.globomantics.test.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.generic.utilities.Logg;
import com.generic.utilities.Utilities;
import com.globomantics.test.base.BrowserFactory;
import com.globomantics.test.base.TestBase;
import com.globomantics.test.pages.HomePage;

public class HomePageTest extends TestBase {

	
	@BeforeClass
	public void openBrowser() throws Exception {
		/*BrowserFactory browserFactory = BrowserFactory.getInstance();
		browserFactory.setDriver();*/
		driver=initialization();
		homePage = new HomePage(driver);
	}

	HomePage homePage;
	private static final Logger LOGGER = Logg.createLogger();

	@Test(priority = 1)
	public void HomePageTitle() {
		homePage = new HomePage(driver);
		LOGGER.info(Utilities.getCurrentThreadId() + "HomePage title is- " + homePage.validateHomPageTitle());
		String title = homePage.validateHomPageTitle();
		Assert.assertEquals(title, "globomantics");
	}

	@Test(priority = 2)
	public void GlobomanticsLogo() {
		homePage = new HomePage(driver);
		LOGGER.info(Utilities.getCurrentThreadId() + "Validating Logo- " + homePage.visibilityOfLogo());
		boolean flag = homePage.visibilityOfLogo();
		Assert.assertTrue(flag);
	}

	@Test(priority = 3)
	public void GlobomanticsEmailIcon() {
		homePage = new HomePage(driver);
		LOGGER.info(Utilities.getCurrentThreadId() + "Validating if the email icon is present or not- "
				+ homePage.visibilityOfEmailIcon());
		boolean flag = homePage.visibilityOfEmailIcon();
		Assert.assertTrue(flag);
	}

	@Test(priority = 4)
	public void GlobomanticsHomePageImage() {
		homePage = new HomePage(driver);
		LOGGER.info(Utilities.getCurrentThreadId() + "Validating if the homepage image is present or not- "
				+ homePage.visibilityOfLogo());
		boolean flag = homePage.visibilityOfHomePageImage();
		Assert.assertTrue(flag);
	}

	@Test(priority = 5)
	public void GlobomanticsEnterDetails() {
		homePage = new HomePage(driver);
		LOGGER.info(Utilities.getCurrentThreadId() + "Enter details: " + '\n' + (prop.getProperty("name") + '\n'
				+ prop.getProperty("emailadrs") + '\n' + prop.getProperty("remarks")));
		homePage.register(prop.getProperty("name"), prop.getProperty("emailadrs"), prop.getProperty("remarks"));

	}
	@AfterClass
	public void quitBrowser() throws Exception {
		LOGGER.info(Utilities.getCurrentThreadId() + "Closing Browser- " + prop.getProperty("browser") + " browser");
		driver.quit();
		//getDriver().quit();
	}	
	

}
