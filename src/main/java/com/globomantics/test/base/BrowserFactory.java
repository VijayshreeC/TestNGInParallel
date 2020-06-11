package com.globomantics.test.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import com.generic.utilities.Logg;
import com.generic.utilities.Utilities;


import com.generic.utilities.Utilities;


public class BrowserFactory {
	private static BrowserFactory instance = null;
	ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	protected static final Logger LOGG = Logg.createLogger();
	public static Properties prop;
	private static final Logger LOGGER = Logg.createLogger();
	public static long Page_Load_time = 160;
	public static long Implicit_wait_time = 200;

	public static BrowserFactory getInstance() {
		if (instance == null) {
			instance = new BrowserFactory();
		}
		return instance;
	}
	
	
	public BrowserFactory() {
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
	public final void setDriver() throws Exception
	{
		String browserName = prop.getProperty("browser");
		LOGGER.info(Utilities.getCurrentThreadId() + "Instantiating/Launching the " + browserName + " Browser");
		DesiredCapabilities caps = null;
		if (browserName.equals("chrome")) {

			caps = DesiredCapabilities.chrome();
			ChromeOptions chOptions = new ChromeOptions();
			Map<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("credentials_enable_service", false);
			chOptions.setExperimentalOption("prefs", chromePrefs);
			chOptions.addArguments("--disable-plugins", "--disable-extensions",
					"--disable-popup-blocking");
			caps.setCapability(ChromeOptions.CAPABILITY, chOptions);
			caps.setCapability("applicationCacheEnabled", false);
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")+"/resources/com/drivers/chromedriver.exe");
			webDriver.set(new ChromeDriver());
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			LOGGER.info(Utilities.getCurrentThreadId() + "Maximize Windows of- " + browserName + " Browser");
			getDriver().manage().window().maximize();
			LOGGER.info(Utilities.getCurrentThreadId() + "Deleting all cookies- " + browserName + " Browser");
			getDriver().manage().deleteAllCookies();
			getDriver().manage().timeouts().pageLoadTimeout(Page_Load_time, TimeUnit.SECONDS);
			getDriver().manage().timeouts().implicitlyWait(Implicit_wait_time, TimeUnit.SECONDS);
			LOGGER.info(Utilities.getCurrentThreadId() + "Navigationg to " + prop.getProperty("url"));
			getDriver().get(prop.getProperty("url"));
			
		} 
		
	}
	public WebDriver getDriver() {
		return webDriver.get();
	}
}
