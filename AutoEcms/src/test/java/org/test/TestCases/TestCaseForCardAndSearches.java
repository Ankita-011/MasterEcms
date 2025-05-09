package org.test.TestCases;

import org.openqa.selenium.By;
import org.test.Base.Base;
import org.test.Pages.Teams;
import org.test.Utilities.WaitUtils;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class TestCaseForCardAndSearches extends Base
{
	ExtentTest test3 = extentReports.createTest("TestcaseForCardAndSearches", "TestCaseThree Started");
	
	@Test
	public void CardInTeams() throws InterruptedException
	{
		Teams card= new Teams(driver,pr);
		ExtentTest test3 = extentReports.createTest("TestcaseForCardAndSearches", "TestCaseThree Started");
        test3.info("Test started");
        
        card.team();
        Thread.sleep(1000);
        test3.info("Clicked On Teams Successfully");
       
        String ScreenshotAddCard = card.cardClickAddCard();
        test3.info("Clicked On Card Successfully");
        Thread.sleep(2000);
        
        WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("BackToTeams")), 10).click();
        Thread.sleep(2000);
        String screenshotEditMember = card.EditTeamMember();
		test3.info("screenshotEditMember");
		
		test3.pass("Screenshot of AddCard:").addScreenCaptureFromPath(ScreenshotAddCard);

	}

}