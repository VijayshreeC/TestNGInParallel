package com.globomantics.test.utilities;

import java.io.File;

import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.globomantics.test.base.TestBase;

public class TestUtil extends TestBase{
	public static long Page_Load_time=160;
	public static long Implicit_wait_time=100;
	
	public  String takeSnapShotAtEnd(String TestCaseName) throws Exception
	{
		File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);//cast driver to screenshot mode
		//String currentDir="/var/jenkins_home/workspace/TestProject1/reports" ; //System.getProperty("user.dir");
		
		String destPath= "/screenshots/"+System.currentTimeMillis()+TestCaseName+".png";
		//String a=currentDir+"/screenshots";
		//System.out.println("This screenshot path", a);
		File file =new File(destPath);
		FileUtils.copyFile(scrFile,file);//copies the file from source to the target
		return destPath;
	}
		
}

/*
TakeScreenshot ts=(TakeScreenshot)driver;
File src=ts.getScreenshotAs(OutputType.FILE)
File*/