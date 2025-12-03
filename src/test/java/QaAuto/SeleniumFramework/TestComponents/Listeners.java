package QaAuto.SeleniumFramework.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import QaAuto.SeleniumFramework.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // thread safe, will save each object in parallel testing
	
	@Override
	public void onTestStart(ITestResult tr) {
		// get all methods by name method
		test = extent.createTest(tr.getMethod().getMethodName());
		extentTest.set(test); // set unique thread ID
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		// get error message
		extentTest.get().fail(tr.getThrowable());
		String pathFileReport = null;
		
		try {
		// This is the code which will help you to get that driver information to Listeners.
		// Fields are associated in class level but not method level.
		// And in that class, we are asking to give me the field of driver, because that field I need to give the life to my screenshot.
		//  assigning the life to this driver and send driver in the makeScreenshot method.
		driver = (WebDriver) tr.getTestClass().getRealClass().getField("driver").get(tr.getInstance());
		}		
		catch (Exception e1){ //Exception mean - no matter what kind of error i got it will be printed
			e1.printStackTrace();
		}
		
		// make screenshot
		try {
			pathFileReport = makeScreenshot(tr.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// attach screenshot in the report.html
		extentTest.get().addScreenCaptureFromPath(pathFileReport, tr.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult tr) {

	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		extentTest.get().log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onFinish(ITestContext context) {
		 
		extent.flush();
	}

}
