package org.test.TestCases;

import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;

import org.test.Base.Base;
import org.test.Pages.Teams;

public class TestCaseForAddMember extends Base {
	ExtentTest test = extentReports.createTest("testCaseForAddMember", "TestCaseOne Started");

	@Test
	public void testCaseForAddMember() throws InterruptedException {
		try {
			Teams addMember = new Teams(driver, pr);
			// ✅ Create the test once
			test.info("Test started");

			addMember.team();
			test.info("Clicked on Teams successfully");

			addMember.AddMember();
			//String addMemberScreenshot=null;
			test.info("Clicked on AddMember successfully");
			//test.info("Screenshot of add member").addScreenCaptureFromPath(addMemberScreenshot);
			
			String responseData = addMember.digitalinvite();
			String[] parts = responseData.split("\\|\\|");
			String responseMsg = parts[0];
			String screenshotPath = parts[1];

			test.info("Invite Message: " + responseMsg);
			test.pass("Screenshot of invite step:")
		    .addScreenCaptureFromPath(screenshotPath.replace("\\", "/"));
			System.out.println("Screenshot saved at: " + screenshotPath);

		} catch (Exception e) {
			System.out.println("Something went wrong");
			test.fail(e);
		}
	}
}