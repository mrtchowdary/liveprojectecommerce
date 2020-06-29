package com.live.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.live.base.Base;

public class ReadPropertiesFile extends Base {
	Properties prop;
	public ReadPropertiesFile(){
		prop = new Properties();
		File excel = new File("./src/main/java/com/live/config/config.properties");
		try {
			FileInputStream fis = new FileInputStream(excel);
			prop.load(fis);
		} catch (Exception e) {
			System.out.println("ReadPropertiesFile - Invalid data file");
		}
	}
	
	public String GetDataFromConfig(String key){
		return prop.getProperty(key);
	}
}
