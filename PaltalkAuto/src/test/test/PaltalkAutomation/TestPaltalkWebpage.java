package PaltalkAutomation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.reusable.ReusableFunction;

import PaltalkPageObjects.ChatRoomPageObjects;
import PaltalkPageObjects.PaltalkAgePages;
import PaltalkPageObjects.PaltalkCheckBox;
import PaltalkPageObjects.PaltalkCheckFilters;
import PaltalkPageObjects.PaltalkDownloadPageObj;
import PaltalkPageObjects.PaltalkFeaturesPageObject;
import PaltalkPageObjects.PaltalkMembersPageObjects;

public class TestPaltalkWebpage {

	public static WebDriver driver;
	public static Properties properties;
	public static FileInputStream fileInputStream;
	public static ExtentReports extent;
	public static ExtentSparkReporter reporter;

	@BeforeTest
	public static Properties paltalkWebpage() throws InterruptedException, IOException {

		driver = new ChromeDriver();
		driver.get("https://www.paltalk.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		Thread.sleep(2000);

		reporter = new ExtentSparkReporter("./TestReport.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);

		fileInputStream = new FileInputStream("config.properties");
		properties = new Properties();
		properties.load(fileInputStream);
		return properties;

	}

	@Test(priority = 1)
	public static void testPaltalkFeaturesPage() throws InterruptedException, IOException {
		PageFactory.initElements(driver, PaltalkFeaturesPageObject.class);
		Thread.sleep(2000);

		PaltalkFeaturesPageObject.clkFeatures.click();
		driver.navigate().refresh();

		ExtentTest classTest = extent.createTest("Features landing page", "This is for valid Test Case at Test Level");
		classTest.info("Features page");
		classTest.pass("Click the page");
		Thread.sleep(2000);
//		ReusableFunction.screenshotCapture(driver, "Features.png");

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File("C:\\Users\\Jasmine\\eclipse-workspace\\PageObjectModel\\Snaps\\Features.png");
		// Copy file from one location to another
		FileUtils.copyFile(src, dest);
		classTest.addScreenCaptureFromPath("Snaps\\Features.png");
	}

	@Test(priority = 2)
	public static void testPaltalkMembersPage() throws InterruptedException, IOException {
		PageFactory.initElements(driver, PaltalkMembersPageObjects.class);
		PaltalkMembersPageObjects.clkMembers.click();
		Thread.sleep(3000);
		PaltalkMembersPageObjects.clkSerachbox.sendKeys(properties.getProperty("Search"));
		Thread.sleep(2000);
		WebElement selectGender = driver.findElement(By.xpath("//input[@value='All Genders']"));
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].value='Male';", selectGender);
		Thread.sleep(3000);
		WebElement selectCountry = driver.findElement(By.xpath("//input[@value='India']"));
		jse.executeScript("arguments[0].value='Iran';", selectCountry);

		ExtentTest membersTest = extent.createTest("Members landing page");
		membersTest.info("Members page");
		membersTest.pass("SelectCountry Field");
		Thread.sleep(2000);
//		ReusableFunction.screenshotCapture(driver, "FeaturesPage");

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File("C:\\Users\\Jasmine\\eclipse-workspace\\PageObjectModel\\Snaps\\SelectCountry.png");
		// Copy file from one location to another
		FileUtils.copyFile(src, dest);
		membersTest.addScreenCaptureFromPath("Snaps\\SelectCountry.png");

	}

	@Test(priority = 3)
	public static void testAge() throws InterruptedException, IOException {
		Thread.sleep(3000);
		PageFactory.initElements(driver, PaltalkAgePages.class);
		PageFactory.initElements(driver, PaltalkCheckBox.class);

		PaltalkAgePages.clkAge.sendKeys(properties.getProperty("age18"));
		PaltalkAgePages.clkAge.clear();
		Thread.sleep(3000);
		PaltalkAgePages.clkAge.sendKeys(properties.getProperty("age"));
		Thread.sleep(6000);
		PaltalkCheckBox.clkCheckBox.click();

		ExtentTest ageTest = extent.createTest("Test Age Field");
		ageTest.info("Members page Age Field");
		ageTest.pass("Age field");
		Thread.sleep(3000);

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File("C:\\Users\\Jasmine\\eclipse-workspace\\PageObjectModel\\Snaps\\Age.png");
		FileUtils.copyFile(source, destination);

		ageTest.addScreenCaptureFromPath("snaps\\Age.png");

	}

	@Test(priority = 4)
	public static void testFilters() throws InterruptedException, IOException {
		PageFactory.initElements(driver, PaltalkCheckFilters.class);
		Thread.sleep(2000);
		PaltalkCheckFilters.clkCheckbox.click();

		ExtentTest ageTest = extent.createTest("Test checkfilters Field");
		ageTest.info("Members checkfilters Field");
		ageTest.pass("Checkfilters field");
		Thread.sleep(3000);

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File("C:\\Users\\Jasmine\\eclipse-workspace\\PageObjectModel\\Snaps\\Checkfilters.png");
		FileUtils.copyFile(source, destination);

		ageTest.addScreenCaptureFromPath("snaps\\Checkfilters.png");

	}

	@Test(priority = 5)
	public static void testChatRoom() throws InterruptedException, AWTException, IOException {
		PageFactory.initElements(driver, ChatRoomPageObjects.class);
		ChatRoomPageObjects.clkChatRoom.click();
		Thread.sleep(10000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		Thread.sleep(4000);
		ChatRoomPageObjects.clkLangSelectField.click();
		Thread.sleep(2000);
		ChatRoomPageObjects.clkBerberLang.click();
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(3000);
		ChatRoomPageObjects.clkRadioField.click();
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(3000);
		ChatRoomPageObjects.clkXMRadio.click();
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(3000);
		ChatRoomPageObjects.clkRatingField.click();
		Thread.sleep(3000);
		ChatRoomPageObjects.clkApplyBtn.click();

		ExtentTest chatRoomTest = extent.createTest("Test ChatRoom Page");
		chatRoomTest.info("Chatroom Field");
		chatRoomTest.pass("Chatroom data entering");
		Thread.sleep(3000);

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File("C:\\Users\\Jasmine\\eclipse-workspace\\PageObjectModel\\Snaps\\chatroom.png");
		FileUtils.copyFile(source, destination);

		chatRoomTest.addScreenCaptureFromPath("snaps\\chatroom.png");
	}

	@Test(priority = 6)
	public static void testProducts() throws IOException, InterruptedException {

		PageFactory.initElements(driver, PaltalkDownloadPageObj.class);

		PaltalkDownloadPageObj.clkProducts.click();

		ExtentTest productsTest = extent.createTest("Test products page");
		productsTest.info("Products Field");
		productsTest.pass("Products field");
		Thread.sleep(3000);

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File("C:\\Users\\Jasmine\\eclipse-workspace\\PageObjectModel\\Snaps\\products.png");
		FileUtils.copyFile(source, destination);

		productsTest.addScreenCaptureFromPath("snaps\\products.png");

	}

	@AfterTest
	public static void afterTest() {

		extent.flush();
		driver.quit();
	}
}
