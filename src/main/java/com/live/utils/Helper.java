package com.live.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.live.base.Base;

public class Helper extends Base{
	
	public static String takeScreenshot(){
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destPath = System.getProperty("user.dir") + "/screenshots/" + DateFormater() + ".png";
		File destFile = new File(destPath);
		try {
			FileUtils.copyFile(scrFile, destFile);
		} catch (IOException e) {
			System.out.println("IOException "+e.getMessage());
		}
		return destPath;
	}
	
	public static String DateFormater(){
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd_HHmmss");
		return sdf.format(System.currentTimeMillis());
	}
	
	public static boolean is32Architecture(){
		int osArch = System.getProperty("os.arch").contains("86")?32:64;
		return (osArch == 32);
	}
	
	public static void staticSleep(long seconds){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
