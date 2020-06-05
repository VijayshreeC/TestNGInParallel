package com.globomantics.test.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;//execute program on VM or remotely
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.net.MalformedURLException;
import com.generic.property.PropertyManager;
import com.generic.utilities.Logg;
import com.generic.utilities.Utilities;
import com.globomantics.test.utilities.TestUtil;
import com.globomantics.test.utilities.WebEventListener;

public class TestBase {
	protected static final Logger LOGG = Logg.createLogger();
	public static WebDriver driver; // accessible to child class
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventlistner;
	private static final Logger LOGGER = Logg.createLogger();
	private static final Properties FRAMEWORKPROPERTY = PropertyManager.loadApplicationPropertyFile();


	// Constructor
	public TestBase() {
		// TODO Auto-generated constructor stub
		try {
			File src = new File(
					System.getProperty("user.dir") + "/src/main/java/com/globomantics/test/config/config.properties");// location
			FileInputStream fs = new FileInputStream(src);

			prop = new Properties();
			prop.load(fs);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void initialization() {

		String browserName = prop.getProperty("browser");
		LOGGER.info(Utilities.getCurrentThreadId() + "Instantiating/Launching the " + browserName + " Browser");
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\swathys\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver(); // driver initialization for child class use
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			LOGGER.info(Utilities.getCurrentThreadId() + "Maximize Windows of- " + browserName + " Browser");
			driver.manage().window().maximize();
			LOGGER.info(Utilities.getCurrentThreadId() + "Deleting all cookies- " + browserName + " Browser");
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtil.Page_Load_time, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtil.Implicit_wait_time, TimeUnit.SECONDS);
			LOGGER.info(Utilities.getCurrentThreadId() + "Navigationg to " + prop.getProperty("url"));
			driver.get(prop.getProperty("url"));
		} else if (browserName.equals("remotechrome")) {
			{
				System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
				DesiredCapabilities dc = new DesiredCapabilities();
				dc.setBrowserName("chrome");

				dc.setPlatform(Platform.LINUX);
				try {
					driver = new RemoteWebDriver(new URL("http://34.77.44.43:4444/wd/hub"), dc);
					} catch (MalformedURLException e) {
//http://34.70.254.170:4444
					System.out.println("Link error");
				}
				System.out.println("RUNNING TESTS IN REMOTE CHROME BROWSER");				
				LOGGER.info(Utilities.getCurrentThreadId() + "Maximize Windows of- " + browserName + " Browser");
				driver.manage().window().maximize();
				LOGGER.info(Utilities.getCurrentThreadId() + "Deleting all cookies- " + browserName + " Browser");
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().pageLoadTimeout(TestUtil.Page_Load_time, TimeUnit.SECONDS);
				LOGGER.info(Utilities.getCurrentThreadId() + "Navigationg to " + prop.getProperty("url"));
				driver.get(prop.getProperty("url"));

			}
		}

	}

}
