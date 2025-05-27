package org.test.TestCases;

import org.testng.Assert;
import org.test.Base.Base;
import org.test.Pages.MisSection;
import org.test.Utilities.CaptureScreenshots;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class TestCaseForMisTrxnSearch extends Base
{
	@Test
	public void TestCaseForMisTrxnSearch() throws InterruptedException {
	    ExtentTest test = extentReports.createTest("testCaseForMISSection", "TestCaseFour Started");

	    try {
	        MisSection misReport = new MisSection(driver, pr, test);
	        Thread.sleep(2000);
	        misReport.transactionReport();
	        
	        Thread.sleep(1000);
	        misReport.filterAndValidateReport("9595950057", "", "", "Last Month", "");

	        // Screenshot on success
	        String screenshotPath = CaptureScreenshots.captureScreenshot(driver, "MIS_Filter_Success");
	        test.pass("MIS Filter applied successfully")
	            .addScreenCaptureFromPath(screenshotPath.replace("\\", "/"));

	    } catch (Exception e) {
	        // Screenshot on failure
	        String screenshotPath = CaptureScreenshots.captureScreenshot(driver, "MIS_Filter_Failure");
	        test.fail("Test failed due to exception: " + e.getMessage())
	            .addScreenCaptureFromPath(screenshotPath.replace("\\", "/"));
	        
	        Assert.fail("Test failed with exception: ", e);
	    }
	}

}
