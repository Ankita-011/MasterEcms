package org.test.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager 
{
	    private static ExtentReports extent;

	    public static ExtentReports getInstance() {
	        if (extent == null) {
	            ExtentSparkReporter spark = new ExtentSparkReporter("Report/ExtentReport.html");
	            extent = new ExtentReports();
	            extent.attachReporter(spark);
	        }
	        return extent;
	    }
	}
