package com.globomantics.test.extentreports;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReport {
	static ExtentReports extent;

	public static ExtentReports ReportGenerator() {
		extent = new ExtentReports();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		String repName = "Test-Report-" + timeStamp ; //+ ".html";
		String path = "/var/jenkins_home/workspace/TestProject1/reports/repName.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Globmantic");
		reporter.config().setDocumentTitle("TestResults");
		String css = ".r-img {width: 450PX; height: 400PX}";
		System.out.println(css);
		reporter.config().setCSS(css);

		//reporter.config().setTheme(Theme.DARK);

		//reporter.loadXMLConfig(new File("/var/jenkins_home/workspace/TestProject1/extent-config.xml"));
		extent = new ExtentReports();

		extent.attachReporter(reporter);
		//extent.loadConfig();
		extent.setSystemInfo("Tester", "XYZ");
		return extent;

	}

}
