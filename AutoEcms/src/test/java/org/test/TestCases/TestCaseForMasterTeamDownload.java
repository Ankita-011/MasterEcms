package org.test.TestCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.IOException;

import org.test.Base.Base;
import org.test.Pages.Teams;
import org.test.Utilities.ExcelComparator;
import com.aventstack.extentreports.ExtentTest;

public class TestCaseForMasterTeamDownload extends Base {
	@Test
	public void compareExcelTest() throws IOException, InterruptedException {
		Teams excelTest= new Teams(driver,pr);
		
		ExtentTest report = extentReports.createTest("TestcaseForMasterTeamDownload", "TestCaseTwo Started");
        report.info("Test started");
        
		Thread.sleep(2000);
		excelTest.team();
		Thread.sleep(5000);
		excelTest.masterTeamSumaryDowmload();
		Thread.sleep(1000);
		
		String expected = "/Users/ankita/Documents/MyProjects/Excels/MasterTeamDownload.xlsx";
		String downloadsDir = System.getProperty("user.home") + "/Downloads";
		File latestFile = ExcelComparator.getLatestFileFromDir(downloadsDir);

		if (latestFile != null) {
		    String actual = latestFile.getAbsolutePath();
		    boolean result = ExcelComparator.compareExcelFiles(expected, actual);
		    AssertJUnit.assertTrue(result);
		    report.info("Actual result is " +result);
		} else {
		    Assert.fail("No downloaded file found!");
		}
	}

}

