package com.globomantics.test.testcases;

import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.generic.utilities.Logg;
import com.generic.utilities.Utilities;
import com.globomantics.test.base.Browser;
import com.globomantics.test.base.TestBase;
import com.globomantics.test.pages.HomePage;
import com.globomantics.test.pages.NetherlandPage;
import com.globomantics.test.utilities.TestUtil;

public class NetherlandPageTest extends TestBase {
	public NetherlandPageTest() {
		super();

	}

	NetherlandPage netherlandPage;
	HomePageTest homePageTest = new HomePageTest();
	HomePage homePage = new HomePage();
	private static final Logger LOGGER = Logg.createLogger();
	public String address = null;
	Browser browser;

	@BeforeClass
	public void openBrowser() {
		initialization();
		netherlandPage = new NetherlandPage();

	}

	@Test(priority = 1)
	public void compareValues() throws Exception {
		LOGGER.info(Utilities.getCurrentThreadId() + "Comparing the address between the home page and the table ");
		netherlandPage.selectFromDropDownN(prop.getProperty("countryvalue1"));

		{
			for (int i = 1; i <= netherlandPage.tablesize(); i++)
				try {
					address = driver.findElement(By.xpath("//*[@class='table table-hover']/tbody/tr[" + i + "]"))
							.getText();
					LOGGER.info(Utilities.getCurrentThreadId() + "Address as displayed in the table " + address);

					// i = i + 2;//Move the selection in the dropdown box
					driver.findElement(By.xpath("//*[@class='table table-hover']/tbody/tr[" + i + "]")).click();
					LOGGER.info(Utilities.getCurrentThreadId() + "Address as displayed in the table in the homepage  "
							+ netherlandPage.addressInHomePage());

					if (address.matches((netherlandPage.addressInHomePage()))) {
						System.out.println("Address matches ");
					} else {
						System.out.println("Address does not match" );
					}
					break;
				} catch (NoSuchElementException e) {
					System.out.println("could not find element ");
				}
		}

	}

	@AfterClass
	public void quitBrowser() {
		LOGGER.info(Utilities.getCurrentThreadId() + "Closing Browser " + prop.getProperty("browser") + " browser");
		driver.quit();
	}
}