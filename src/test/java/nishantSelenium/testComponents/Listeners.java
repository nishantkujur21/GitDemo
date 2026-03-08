package nishantSelenium.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import nishantSelenium.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest> ();
	
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	test = extent.createTest(result.getMethod().getMethodName());
	extentTest.set(test);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test Passed!");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		// TODO Auto-generated method stub
		  
		extentTest.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} 
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String filepath= null;
		
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());

	}

	// Print the actual error to console first
//	System.out.println("============ TEST FAILED ============");
//	System.out.println("Test Name: " + result.getMethod().getMethodName());
//	System.out.println("Error: " + result.getThrowable());
//	result.getThrowable().printStackTrace();
//	System.out.println("====================================");
//	
//	// Create test object if it's null
//	if(test == null) {
//		test = extent.createTest(result.getMethod().getMethodName());
//	}
//	
//	test.fail(result.getThrowable());
//	
//	try {
//		driver = (WebDriver)result.getTestClass().getRealClass().getField("driver")
//				.get(result.getInstance());
//	}
//	catch (Exception e1) {
//		e1.printStackTrace();
//	}
//	
//	String filepath = null;
//	
//	try {
//		filepath = getScreenshot(result.getMethod().getMethodName(), driver);
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//	test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
//
//}
	
	
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

		extent.flush();
	}







}
