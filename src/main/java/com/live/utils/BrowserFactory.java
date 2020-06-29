package com.live.utils;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.live.base.Base;

public class BrowserFactory extends Base{

	public WebDriver InitializeDriver(String browser){
		if(browser.toLowerCase().equalsIgnoreCase("chrome")){
			if(Helper.is32Architecture()) WebDriverManager.chromedriver().arch32().setup();
			else WebDriverManager.chromedriver().arch64().setup();
			driver=new ChromeDriver();
		}else if(browser.toLowerCase().equalsIgnoreCase("firefox")){
			if(Helper.is32Architecture()) WebDriverManager.firefoxdriver().arch32().setup();
			else WebDriverManager.firefoxdriver().arch64().setup();
			driver = new FirefoxDriver();
		}else if(browser.toLowerCase().equalsIgnoreCase("ie")){
			if(Helper.is32Architecture()) WebDriverManager.iedriver().arch32().setup();
			else WebDriverManager.iedriver().arch64().setup();
			driver = new InternetExplorerDriver();
		}
		
		e_driver = new EventFiringWebDriver(driver);		
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().pageLoadTimeout(Utils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		driver.get(prop.GetDataFromConfig("url"));
		return driver;
	}
}
