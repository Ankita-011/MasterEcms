package org.test.Pages;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.test.Utilities.CsvReader;
import org.test.Utilities.WaitUtils;
import org.test.Utilities.ZipFiles;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

public class MisSection {
	 ChromeDriver driver;
	    Properties pr;
	    ExtentTest test;
	 
	    public MisSection(ChromeDriver driver, Properties pr,ExtentTest test) {
	        this.driver = driver;
	        this.pr = pr;
	        this.test= test;
	    }
	    public void transactionReport() throws InterruptedException {
	    	//WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("MisTab")), 10).click();
	    	driver.findElement(By.xpath(pr.getProperty("MisTab"))).click();
	    	System.out.println("MISTab is clicked");
	    	Thread.sleep(1000);
	    }
	    public void filterAndValidateReport(String mobileNumber, String employeeId, String transactionId, String dateRange, String approvalStatusToVerify) throws InterruptedException, IOException {
	        // 1. Enter Mobile Number (if provided)
	        if (mobileNumber != null && !mobileNumber.isEmpty()) {
	        	 WebElement mobileNoInput = driver.findElement(By.id(pr.getProperty("SearchMobileNumber")));
	        	 mobileNoInput.sendKeys(mobileNumber);
	        	 Thread.sleep(1000);
	        }
	     // 2. Enter Employee ID (if provided)
	        if (employeeId != null && !employeeId.isEmpty()) {
	            WebElement employeeIdInput = driver.findElement(By.id(pr.getProperty("SearchempId")));
	            employeeIdInput.sendKeys(employeeId);
	            Thread.sleep(500);
	        }
	
	    // 3. Enter Transaction ID (if provided)
        	if (transactionId != null && !transactionId.isEmpty()) {
        		WebElement transactionIdInput = driver.findElement(By.id(pr.getProperty("SearchTrxnId")));
        		transactionIdInput.sendKeys(transactionId);
        		Thread.sleep(500);;
        }
        	// 4. Select Date Range (if provided)
            if (dateRange != null && !dateRange.isEmpty()) {
            	WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("SearchStartDate")), 10).click();
            	Thread.sleep(1000);
            	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            	FileUtils.copyFile(scrFile, new File("screenshot.png"));


                // Assuming 'dateRange' can be one of the predefined options like "Today", "Last 7 Days", etc.
                WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("SearchLastMonth")), 10).click();
                Thread.sleep(1000);
                File scroneFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File("screenshot.png"));

                

                // Click the "Select" button on the date picker
                WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("SearchSelect")), 10).click();
            }
         // 5. Click the "Submit" button to apply filters
            WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("TrxnSubmit")), 10).click();
            Thread.sleep(500);
	    
	    // 6. Wait for the report table to load (adjust locator based on your actual table)
            WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("TrxnTable")), 10);
            System.out.println("REPORT is Visible");

        // 7. Validate the report based on the applied filters and expected results
	    	List<WebElement> reportRows = driver.findElements(By.xpath("//table[contains(@class, 'align-middle')]//tbody/tr"));

	    	for (WebElement row : reportRows) {
	    	    List<WebElement> columns = row.findElements(By.tagName("td"));

	    	        // Mobile/Card No. in 6th column
	    	        if (mobileNumber != null && !mobileNumber.isEmpty()) {
	    	            String mobileCardNo = columns.get(5).getText();
	    	            Assert.assertTrue(mobileCardNo.contains(mobileNumber), "Mobile/Card No. mismatch: " + mobileCardNo);
	    	            test.pass("✅ Mobile/Card No. matched: " + mobileCardNo);
	    	        }

	    	        // Employee ID in 4th column
	    	        if (employeeId != null && !employeeId.isEmpty()) {
	    	            String employeeIdDeptt = columns.get(3).getText();
	    	            Assert.assertTrue(employeeIdDeptt.contains(employeeId), "Employee ID mismatch: " + employeeIdDeptt);
	    	            test.pass("✅ Employee ID matched: " + employeeIdDeptt);
	    	        }

	    	        // Transaction ID in 1st column
	    	        if (transactionId != null && !transactionId.isEmpty()) {
	    	            String transactionIdCell = columns.get(0).getText();
	    	            Assert.assertTrue(transactionIdCell.contains(transactionId), "Transaction ID mismatch: " + transactionIdCell);
	    	            test.pass("✅ Transaction ID matched: " + transactionIdCell);
	    	        }

	    	        // Approval Status in 10th column
	    	        if (approvalStatusToVerify != null && !approvalStatusToVerify.isEmpty()) {
	    	            String approvalStatus = columns.get(9).getText().trim();
	    	            Assert.assertEquals(approvalStatus, approvalStatusToVerify, "Approval Status mismatch: " + approvalStatus);
	    	            test.pass("✅ Approval Status matched: " + approvalStatus);
	    	        }

	    	   
	    	}

	    	test.info("✅ Report filtered and validated successfully for the given criteria.");
	    }
	    public void transactionAllReport() throws InterruptedException {
	    	WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("TrxnAllTab")),10).click();
	    	System.out.println("Transaction All Tab is clicked");
	    	Thread.sleep(1000);
	    }

	    public void transactionAll(String mobileNumber, String employeeId, String transactionId, String dateRange, String approvalStatusToVerify) throws Exception {
	    	// 1. Enter Mobile Number (if provided)
	        if (mobileNumber != null && !mobileNumber.isEmpty()) {
	        	 WebElement mobileNoInput = driver.findElement(By.id(pr.getProperty("SearchMobileNumber")));
	        	 mobileNoInput.sendKeys(mobileNumber);
	        	 Thread.sleep(1000);
	        }
	     // 2. Enter Employee ID (if provided)
	        if (employeeId != null && !employeeId.isEmpty()) {
	            WebElement employeeIdInput = driver.findElement(By.id(pr.getProperty("SearchempId")));
	            employeeIdInput.sendKeys(employeeId);
	            Thread.sleep(500);
	        }
	
	    // 3. Enter Transaction ID (if provided)
        	if (transactionId != null && !transactionId.isEmpty()) {
        		WebElement transactionIdInput = driver.findElement(By.id(pr.getProperty("SearchTrxnId")));
        		transactionIdInput.sendKeys(transactionId);
        		Thread.sleep(500);;
        }
        	// 4. Select Date Range (if provided)
            if (dateRange != null && !dateRange.isEmpty()) {
            	WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("SearchStartDate")), 10).click();

                // Assuming 'dateRange' can be one of the predefined options like "Today", "Last 7 Days", etc.
                WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("SearchLastMonth")), 10).click();
                Thread.sleep(1000);
                

                // Click the "Select" button on the date picker
                WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("SearchSelect")), 10).click();
            }
         // 5. Click the "Submit" button to apply filters
            List<WebElement> allSubmitButtons = driver.findElements(By.xpath(pr.getProperty("TrxnAllSubmit")));

            if (!allSubmitButtons.isEmpty() && allSubmitButtons.get(0).isDisplayed()) {
                // Submit button is visible
                WebElement allSubmitButton = allSubmitButtons.get(0);
                allSubmitButton.click();
                Thread.sleep(500);
            } else {
                // Submit button not found or not visible, try Download
                WebElement downloadButton = WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("TrxnAllDownload")), 10);
                downloadButton.click();
                Thread.sleep(500);
            }

	    
	    // 6. Wait for the report table to load (adjust locator based on your actual table)
            WaitUtils.waitForElementToBeClickable(driver, By.xpath(pr.getProperty("TrxnAllDownloadButton")), 10).click();
            Thread.sleep(2000);
            System.out.println("REPORT is Visible");
            
	    	
            String downloadDir = "/Users/ankita/Downloads"; // Set to your system download path
            String unzipDirPath = downloadDir + "/unzipped"; // Or any temp folder

            // Get the latest downloaded ZIP file
            File zipFile = ZipFiles.getLatestZipFile(downloadDir);

            // Use utility to extract and validate
            File extractedFile = ZipFiles.unzipFile(zipFile, unzipDirPath);

            if (extractedFile != null) {
                String fileName = extractedFile.getName();

                if (fileName.endsWith(".csv") || fileName.endsWith(".xlsx")) {

                    ZipFiles.validateZipReport(
                    	downloadDir,  // should pass downloadDir, not zipFile
                        mobileNumber,
                        employeeId,
                        transactionId,
                        approvalStatusToVerify,
                        test
                    );
                } else {
                    test.fail("❌ Unsupported file type: " + fileName);
                }
            } else {
                test.fail("❌ No extracted file found from ZIP.");
            }
}
}