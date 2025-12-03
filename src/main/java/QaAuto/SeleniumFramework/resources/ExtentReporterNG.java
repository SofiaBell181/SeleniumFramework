package QaAuto.SeleniumFramework.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	public static ExtentReports getReportObject() {
		// save in the project folder - reports
		String projectPath = System.getProperty("user.dir");
		String reportPath = projectPath + "//reports//index.html";

//		String pathreport = System.getProperty("user.dir"+"//reports//index.html");
		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
		spark.config().setReportName("Web Automation Results2");
		spark.config().setDocumentTitle("Test Case Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Tester", "Sofia");
		return extent;
	}
}
