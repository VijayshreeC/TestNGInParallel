package com.globomantics.test.extentreports;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.globomantics.test.base.TestBase;


public class ExtentListner extends TestBase implements ITestListener 
{
	ExtentReports extent = ExtentReport.ReportGenerator();
	ExtentTest test;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
		try {
			extentTest.get().addScreenCaptureFromPath(takeSnapShotAtEnd(result.getMethod().getMethodName(),driver),
					result.getMethod().getMethodName());
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		extentTest.get().fail(result.getThrowable());
		Object testObject = result.getInstance();
		System.out.println("Object Instance " +testObject.toString());
		Class cl = result.getTestClass().getRealClass();
		System.out.println("Class name" +cl);
		try {
			driver = (WebDriver)cl.getDeclaredField("driver").get(testObject);
			 
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			extentTest.get().addScreenCaptureFromPath(takeSnapShotAtEnd(result.getMethod().getMethodName(), driver),
					result.getMethod().getMethodName());
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extentTest.get().log(Status.SKIP, "Skipped");
		try {
			test.addScreenCaptureFromPath(takeSnapShotAtEnd(result.getMethod().getMethodName(),driver),
					result.getMethod().getMethodName());
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		extentTest.get().log(Status.PASS, "Successful");
		try {
			test.addScreenCaptureFromPath(takeSnapShotAtEnd(result.getMethod().getMethodName(),driver),
					result.getMethod().getMethodName());
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	@Override
	public void onStart(ITestContext context) {
	}
}