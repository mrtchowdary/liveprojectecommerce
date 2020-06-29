package com.live.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.live.utils.BrowserFactory;
import com.live.utils.Helper;
import com.live.utils.ReadExcelData;
import com.live.utils.ReadPropertiesFile;
import com.live.utils.WebEventListener;

public class Base {
	
	public static WebDriver driver;
	public static ReadExcelData excel;
	public static ReadPropertiesFile prop;
	public static WebEventListener eventListener;
	public static EventFiringWebDriver e_driver;
	public static ExtentReports report;
	public ExtentTest logger;
	
	@BeforeSuite(alwaysRun = true)
	public void SetupSuite(){
		excel = new ReadExcelData();
		prop = new ReadPropertiesFile();
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./reports/ecom_"+Helper.DateFormater()+".html");
		report = new ExtentReports();
		report.attachReporter(htmlReporter);
	}
	
	@BeforeMethod(alwaysRun = true)
	public void SetupMethod(){
		driver = new BrowserFactory().InitializeDriver(prop.GetDataFromConfig("browser"));
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDownMethod(ITestResult result) throws Exception{
		if(result.getStatus() == ITestResult.SUCCESS)
			logger.pass("Test passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.takeScreenshot()).build());
		else if(result.getStatus() == ITestResult.FAILURE)
			logger.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.takeScreenshot()).build());
		report.flush();
		driver.close();
	}
}
