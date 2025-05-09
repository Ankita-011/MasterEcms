package org.test.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.test.Utilities.ExtentManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import com.aventstack.extentreports.ExtentReports;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
    public ChromeDriver driver;
    public Properties pr;
    public ExtentReports extentReports= ExtentManager.getInstance();

    @BeforeClass
    public void init() throws IOException, InterruptedException {
    	
        // Load object repository
        File f = new File("ObjectRepo.properties"); // Adjust path if needed
        FileInputStream fi = new FileInputStream(f);
        pr = new Properties();
        pr.load(fi);

        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://demoecms.s3-website.ap-south-1.amazonaws.com/#/login");

        // Perform login steps
        boolean logo = driver.findElement(By.xpath("//img[@alt='logo']")).isDisplayed();
        System.out.println("Logo displayed: " + logo);
        driver.findElement(By.xpath(pr.getProperty("Login"))).sendKeys("stay.master@eroute.in");
        Thread.sleep(1000);
        driver.findElement(By.xpath(pr.getProperty("Password"))).sendKeys("Staymaster@123");
        Thread.sleep(10000);
        driver.findElement(By.xpath(pr.getProperty("Submit"))).click();
        Thread.sleep(5000);
    }

    @AfterClass
    public void browserClose() {
        if (driver != null) {
            driver.quit();
        }
    }
    @AfterSuite
    public void tearDownReport() {
        ExtentReports extent = ExtentManager.getInstance();
        if (extent != null) {
            extent.flush();
        }
    }
}
