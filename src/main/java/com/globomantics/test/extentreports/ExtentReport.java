package com.globomantics.test.extentreports;




import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReport {
	static ExtentReports extent;

	public static ExtentReports ReportGenerator() {
		extent = new ExtentReports();

		String path = "/var/jenkins_home/workspace/TestProject1/reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Globmantic");
		reporter.config().setDocumentTitle("TestResults");
	

		//reporter.config().setTheme(Theme.DARK);

		//reporter.loadXMLConfig(new File("/var/jenkins_home/workspace/TestProject1/extent-config.xml"));
		extent = new ExtentReports();

		extent.attachReporter(reporter);
		//extent.loadConfig();
		extent.setSystemInfo("Tester", "XYZ");
		return extent;

	}

}
