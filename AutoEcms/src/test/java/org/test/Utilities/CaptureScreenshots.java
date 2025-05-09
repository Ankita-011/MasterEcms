package org.test.Utilities;

import java.io.File;
import java.io.IOException;
 
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshots {

	    public static String captureScreenshot(WebDriver driver, String screenshotName) {
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File src = ts.getScreenshotAs(OutputType.FILE);
	        String path = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
	        try {
	            FileUtils.copyFile(src, new File(path));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return path;
	    }
	

}
