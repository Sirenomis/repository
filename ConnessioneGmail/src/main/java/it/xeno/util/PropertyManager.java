package it.xeno.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
	
	private static final String PATH = "src/main/resources/";
	private static final String CONFIG_FILE = "config.properties";
	private static Properties config;
	private static PropertyManager propertyManager;
	
	private PropertyManager(){
		init();
	}
	
	private static void init(){
		FileInputStream configFis;
		try {
			
			configFis = new FileInputStream(PATH + CONFIG_FILE);
			config = new Properties();
			config.load(configFis);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized PropertyManager getInstance(){
		if(propertyManager == null){
			return new PropertyManager();
		} 
		
		return propertyManager;
	}
	
	public String getConfig(String key){
		return config.getProperty(key);
	}

	public static void main(String[] args) {
		String msg = PropertyManager.getInstance().getConfig("mail.password");
		System.out.println(msg);
	}
	
}
