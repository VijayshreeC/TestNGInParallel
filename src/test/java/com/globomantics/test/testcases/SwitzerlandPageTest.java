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
import com.globomantics.test.pages.NetherlandPage;
import com.globomantics.test.pages.SwitzerlandPage;
import com.globomantics.test.utilities.TestUtil;

public class SwitzerlandPageTest extends TestBase {
	/*
	 * public SwitzerlandPageTest() { super();
	 * 
	 * }
	 */
	SwitzerlandPage switzerlandPage;
	HomePageTest homePageTest = new HomePageTest();
	private static final Logger LOGGER = Logg.createLogger();
	public String address = null;
	Browser browser;

	@BeforeClass
	public void openBrowser() {
		initialization();
		switzerlandPage = new SwitzerlandPage();

	}

	@Test(priority = 1)
	public void CompareAddressOfSwitzerland() throws TimeoutException

	{
		LOGGER.info(Utilities.getCurrentThreadId() + "Comparing the address between the home page and the table ");
		switzerlandPage.selectFromDropDownN(prop.getProperty("countryvalue2"));

		{

			for (int i = 1; i <= switzerlandPage.tablesize(); i++)
				try {
					address = driver.findElement(By.xpath("//*[@class='table table-hover']/tbody/tr[" + i + "]"))
							.getText();
					LOGGER.info(Utilities.getCurrentThreadId() + "Address as displayed in the table " + address);

					driver.findElement(By.xpath("//*[@class='table table-hover']/tbody/tr[" + i + "]")).click();
					LOGGER.info(Utilities.getCurrentThreadId() + "Address as displayed in the table in the homepage  "
							+ switzerlandPage.addressInHomePage());

					if (address.matches((switzerlandPage.addressInHomePage()))) {
						System.out.println("Address matches");
					} else {
						System.out.println("Address does not match");
					}

					break;
				} catch (NoSuchElementException e) {
					System.out.println("could not find element");
				}
		}
	}

	@AfterClass
	public void quitBrowser() {
		LOGGER.info(Utilities.getCurrentThreadId() + "Closing Browser " + prop.getProperty("browser") + " browser");
		driver.quit();
	}

}
