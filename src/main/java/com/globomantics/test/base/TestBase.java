package com.globomantics.test.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;//execute program on VM or remotely
import java.net.MalformedURLException;
import com.generic.utilities.Logg;
import com.generic.utilities.Utilities;

public class TestBase {
	protected static final Logger LOGG = Logg.createLogger();
	public static Properties prop;
	private static final Logger LOGGER = Logg.createLogger();
	public static long Page_Load_time = 160;
	public static long Implicit_wait_time = 200;
	// protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public WebDriver driver;

	public TestBase() {
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

	public WebDriver initialization() {
		String browserName = prop.getProperty("browser");
		LOGGER.info(Utilities.getCurrentThreadId() + "Instantiating/Launching the " + browserName + " Browser");

		if (browserName.equals("chrome")) {
			// WebDriverManager.chromedriver().setup();

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/resources/com/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");

		} else if (browserName.equals("remotechrome")) {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName("chrome");

			dc.setPlatform(Platform.LINUX);
			try {
				driver = new RemoteWebDriver(new URL("http://34.77.44.43:4444/wd/hub"), dc);// http://34.70.254.170:4444
				// http://34.77.44.43:4444
			} catch (MalformedURLException e) {

				System.out.println("Link error");
			}
		}
		System.out.println("RUNNING TESTS IN REMOTE CHROME BROWSER");
		LOGGER.info(Utilities.getCurrentThreadId() + "Maximize Windows of- " + browserName + " Browser");
		driver.manage().window().maximize();
		LOGGER.info(Utilities.getCurrentThreadId() + "Deleting all cookies- " + browserName + " Browser");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Page_Load_time, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Implicit_wait_time, TimeUnit.SECONDS);
		LOGGER.info(Utilities.getCurrentThreadId() + "Navigationg to " + prop.getProperty("url"));
		driver.get(prop.getProperty("url"));
		return driver;
	}

	public String takeSnapShotAtEnd(String TestCaseName, WebDriver driver) throws Exception {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);// cast driver to screenshot mode
		String destPath = "reports/screenshots/" + System.currentTimeMillis() + TestCaseName + ".png";
		String imagePath = "screenshots/" + System.currentTimeMillis() + TestCaseName + ".png";
		File file = new File(destPath);
		FileUtils.copyFile(scrFile, file);
		return imagePath;
	}
}
