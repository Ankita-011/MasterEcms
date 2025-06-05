package org.test.TestCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.test.Base.Base;
import org.test.Pages.MisSection;
import org.test.Utilities.CaptureScreenshots;
import com.aventstack.extentreports.ExtentTest;

public class TestCaseForTransactionAll extends Base
{
	@Test
	public void TestCaseForTransactionAllReport()
	{
		ExtentTest test = extentReports.createTest("testCaseForMISTransactionAllSection", "TestCaseFive Started");

	    try {
	        MisSection misAllReport = new MisSection(driver, pr, test);
	        Thread.sleep(2000);
	        misAllReport.transactionReport();
	        
	        Thread.sleep(1000);
	        misAllReport.transactionAllReport();
	        
	        Thread.sleep(2000);
	        misAllReport.transactionAll("9595950057", "", "", "Last Month", "");
	        // Screenshot on success
	        String screenshotPath = CaptureScreenshots.captureScreenshot(driver, "MISTrxnAll_Filter_Success");
	        test.pass("MISTrxnAll Filter applied successfully")
	            .addScreenCaptureFromPath(screenshotPath.replace("\\", "/"));

	    } catch (Exception e) {
	        // Screenshot on failure
	        String screenshotPath = CaptureScreenshots.captureScreenshot(driver, "MISTrsnAll_Filter_Failure");
	        test.fail("Test failed due to exception: " + e.getMessage())
	            .addScreenCaptureFromPath(screenshotPath.replace("\\", "/"));
	        
	        Assert.fail("Test failed with exception: ", e);
	  
	    }
	}
}
