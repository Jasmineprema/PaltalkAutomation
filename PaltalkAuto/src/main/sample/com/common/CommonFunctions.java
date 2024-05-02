package com.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import PageObjects.LoginPage;

public class CommonFunctions {

	public static WebDriver driver;
	public static Properties properties;
	public static FileInputStream fileInputStream;

	public Properties loadPropertyFile() throws IOException {

		fileInputStream = new FileInputStream("config.properties");
		properties = new Properties();
		properties.load(fileInputStream);

		return properties;
	}

	@BeforeSuite
	public void launchBrowser() throws InterruptedException, IOException {

		loadPropertyFile();

		String browser = properties.getProperty("browser");

		String url = properties.getProperty("url");

		if (browser.equalsIgnoreCase("Chrome")) {

			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();
		}

		driver.get(url);

		driver.manage().window().maximize();

		Thread.sleep(4000);
	}

//	public static void validDatas() {
//
//		String className = properties.getProperty("className");
//
//		String orderId = properties.getProperty("orderId");
//
//		String sectionName = properties.getProperty("section");
//
//		String totalMarkEntry = properties.getProperty("totalMark");
//
//	}

	@AfterSuite
	public void closeBrowser() {

		driver.quit();

	}

}
