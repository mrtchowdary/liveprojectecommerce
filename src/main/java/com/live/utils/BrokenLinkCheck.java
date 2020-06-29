package com.live.utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.live.base.Base;

public class BrokenLinkCheck extends Base{
	
	public BrokenLinkCheck(){
		HttpURLConnection huc = null;
		String url = "";
		int responseCode = 0;
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		
		Iterator<WebElement> it = links.iterator();
		
		while(it.hasNext()){
			url = it.next().getAttribute("href");
			
			if(url==null || url.isEmpty()){
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}
			
			try {
				huc = (HttpURLConnection)new URL(url).openConnection();
				huc.connect();
				responseCode = huc.getResponseCode();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(responseCode>=400) System.out.println("Broken link - "+url);
            else System.out.println("Valid link - "+url);
		}
	}

}
