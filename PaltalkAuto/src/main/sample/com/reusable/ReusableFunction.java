package com.reusable;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ReusableFunction {

	public static void screenshotCapture(WebDriver driver, String screenshotName) {

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		try {
			File destination = new File("C:\\Users\\Jasmine\\eclipse-workspace\\PageObjectModel\\Snaps\\Features.png" + screenshotName);
			FileUtils.copyFile(source,destination);
			System.out.println("Screenshot captured: " + screenshotName);
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot: " + e.getMessage());
		}
	}
}
