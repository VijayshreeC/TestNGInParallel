package com.globomantics.test.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 

public class ExtentReport
{
	static ExtentReports extent;
	public static ExtentReports ReportGenerator()
	{
		//extent report,//exteextentspark
		  String path =System.getProperty("user.dir")+"\\reports\\index.html";
		  ExtentSparkReporter reporter=new ExtentSparkReporter(path);//create html file and config
		  reporter.config().setReportName("Globmantic");
		  reporter.config().setDocumentTitle("TestResults");

		//main class
		    extent=new ExtentReports();
		  extent.attachReporter(reporter);//attach objects to main class
		  extent.setSystemInfo("Tester", "XYZ");
		  return extent;


	}
	
	

}
