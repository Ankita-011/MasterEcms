package org.test.Pages;


import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.test.Utilities.CaptureScreenshots;
import org.test.Utilities.LogsCapture;
import org.test.Utilities.WaitUtils;

import com.aventstack.extentreports.ExtentReports;

public class Teams 
	{
		
		    ChromeDriver driver;
		    Properties pr;
		    ExtentReports extentReports;
		 
		    public Teams(ChromeDriver driver, Properties pr) {
		        this.driver = driver;
		        this.pr = pr;
		    }
		 
		    public void team() {
		    	WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("Teams")), 10).click();
		        //driver.findElement(By.xpath(pr.getProperty("Teams"))).click();
		    }
		 
		    public void searchWithName(String mobile) throws InterruptedException {
		        WebElement mobileNumber = driver.findElement(By.xpath(pr.getProperty("MobileNumber")));
		        mobileNumber.sendKeys(mobile);
		        Thread.sleep(5000);
		        driver.findElement(By.xpath(pr.getProperty("CrossButton"))).click();
		    }
		 
		    public void AddMember() throws InterruptedException {
		    	WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("AddMember")), 10).click();
		    	//driver.findElement(By.xpath(pr.getProperty("AddMember"))).click();
		    	LogsCapture.takeLog("Teams", "Response Message - Add Member Button cicked");
		    	Thread.sleep(5000);
		    }
		 
		    public void dropDown() {
		        driver.findElement(By.xpath(pr.getProperty("Storedropdown"))).click();
		        driver.findElement(By.xpath(pr.getProperty("StoreValue"))).click();
		    }
		 
		    public String digitalinvite() throws InterruptedException {
		        driver.findElement(By.xpath(pr.getProperty("MobileNumber"))).sendKeys("9100078765");
		        driver.findElement(By.id(pr.getProperty("EmailId"))).sendKeys("test@yahoo.com");
		        driver.findElement(By.xpath(pr.getProperty("FirstName"))).sendKeys("Ankita");
		        driver.findElement(By.xpath(pr.getProperty("LastName"))).sendKeys("Saxena");
		        driver.findElement(By.xpath(pr.getProperty("EmployeeId"))).sendKeys("Test_011");
		 
		        Thread.sleep(1000);
		        driver.findElement(By.xpath(pr.getProperty("SelectStore"))).click();
		        driver.findElement(By.xpath(pr.getProperty("Store"))).click();
		        Thread.sleep(1000);
		        driver.findElement(By.xpath(pr.getProperty("SelectDepartment"))).click();
		        //Thread.sleep(1000);
		        driver.findElement(By.xpath(pr.getProperty("DepartmentQA"))).click();
		        driver.findElement(By.xpath(pr.getProperty("AddMemberSubmit"))).click();
		 
		        Thread.sleep(2000);
		        String responseMessage = driver.findElement(By.xpath(pr.getProperty("RespMsg"))).getText();
		        String screenshotPath = CaptureScreenshots.captureScreenshot(driver, "Testcase1");
		        Thread.sleep(2000);
		        System.out.println("Response Message - " + responseMessage);
		        LogsCapture.takeLog("Teams", "Response Message - " + responseMessage);
		        return responseMessage + "||" + screenshotPath;
		    }
		 
		    public void physicalinvite(String physicalMob) throws InterruptedException {
		        driver.findElement(By.id(pr.getProperty("Physical"))).click();
		        WebElement physicalMobile = driver.findElement(By.id(pr.getProperty("EnterMob")));
		        physicalMobile.sendKeys(physicalMob);
		 
		        driver.findElement(By.id(pr.getProperty("FirstName"))).sendKeys("Ankita");
		        driver.findElement(By.id(pr.getProperty("LastName"))).sendKeys("Saxena");
		        driver.findElement(By.id(pr.getProperty("EmployeeId"))).sendKeys("Test_011");
		 
		        driver.findElement(By.xpath(pr.getProperty("Department"))).click();
		        driver.findElement(By.id(pr.getProperty("SelectDept"))).click();
		        driver.findElement(By.xpath(pr.getProperty("Submit"))).click();
		 
		        Thread.sleep(2000);
		    }
		 
		    public String cardClickAddCard() throws InterruptedException {
		        WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("CardInTeams")), 10).click();
		        WaitUtils.waitForElementToBeClickable(driver,By.xpath(pr.getProperty("AddCard")),10).click();
		        WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("CardName")), 10).sendKeys("TestOne");
		        Thread.sleep(1000);
		        WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("AddCardProceed")),10).click();
		        Thread.sleep(1000);
		        String screenshotAddCard = CaptureScreenshots.captureScreenshot(driver, "Testcase3");
		        return screenshotAddCard;

		    }
		        
		    
		 
		    public String EditTeamMember() {
		    	WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("EditTeamMember")),10).click();
		    	WaitUtils.waitForElementToBeClickable(driver, By.xpath("FName"),10).sendKeys("Test");
		    	WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("EditSubmit")),10).click();
		    	WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("EditUpdated")),10);
		        String screenshotEditMember = CaptureScreenshots.captureScreenshot(driver, "TestCase3.1");
		        return screenshotEditMember;
		    }
		 
		    public void cardControls() {
		        driver.findElement(By.xpath(pr.getProperty("CardInTeams"))).click();
		        driver.findElement(By.xpath(pr.getProperty("allowAddMoney"))).click();
		        driver.findElement(By.xpath(pr.getProperty("CCSubmit"))).click();
		    }
		 
		    public String searchMobilenumber() throws InterruptedException {
		        driver.findElement(By.xpath(pr.getProperty("SearchMob"))).sendKeys("9100078765");
		        Thread.sleep(1000);
		        String resmsg= driver.findElement(By.xpath(pr.getProperty("NoData"))).getText();
		        System.out.println("Response Message - " + resmsg);
		        LogsCapture.takeLog("Teams", "Response Message - " + resmsg);
		        Thread.sleep(500);
		        return resmsg;
		    }
		    
		    public void masterTeamSumaryDowmload() throws InterruptedException 
		    {
		    	driver.findElement(By.xpath(pr.getProperty("MasterTeamDownloadTeams"))).click();
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath(pr.getProperty("MasterTeamDownloadReports"))).click();
		    	Thread.sleep(1000);
		    	
		    }
		}




