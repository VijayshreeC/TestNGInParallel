package com.globomantics.test.utilities;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.globomantics.test.base.TestBase;


public class TestUtil extends TestBase{
	public static long Page_Load_time=160;
	public static long Implicit_wait_time=100;
	
	public String takeSnapShotAtEnd(String TestCaseName) throws Exception
	{
		File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);//cast driver to screenshot mode	
		String destPath= "reports/screenshots/"+System.currentTimeMillis()+TestCaseName+".png";
		String imagePath="screenshots/"+System.currentTimeMillis()+TestCaseName+ ".png";
		File file =new File(destPath);
		FileUtils.copyFile(scrFile,file);
		return imagePath;
	}
		
}
