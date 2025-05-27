package org.test.Utilities;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {


	    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator, int timeoutSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	        return wait.until(ExpectedConditions.elementToBeClickable(locator));
	    }

	    public static WebElement waitForVisibility(WebDriver driver, By locator, int timeoutSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }

	    public static boolean waitForInvisibility(WebDriver driver, By locator, int timeoutSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	    }

	}
