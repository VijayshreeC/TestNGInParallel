package com.globomantics.test.extentreports;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.globomantics.test.utilities.TestUtil;

public class ExtentListner extends TestUtil implements ITestListener {
	ExtentReports extent = ExtentReport.ReportGenerator();
	ExtentTest test;
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test.log(Status.PASS, "Test Passed");
		try {
			test.addScreenCaptureFromPath(takeSnapShotAtEnd(result.getMethod().getMethodName()),
					result.getMethod().getMethodName());
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {

		test.fail(result.getThrowable());
		try {
			test.addScreenCaptureFromPath(takeSnapShotAtEnd(result.getMethod().getMethodName()),
					result.getMethod().getMethodName());
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		test.log(Status.SKIP, "Skipped");
		try {
			test.addScreenCaptureFromPath(takeSnapShotAtEnd(result.getMethod().getMethodName()),
					result.getMethod().getMethodName());
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		test.log(Status.PASS, "Successfull");
		try {
			test.addScreenCaptureFromPath(takeSnapShotAtEnd(result.getMethod().getMethodName()),
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