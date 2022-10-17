package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

	public String configReader(String browserdata) throws IOException {
		
		File f = new File("configuration","config.properties");
		
		FileInputStream fis = new FileInputStream(f);
		
		Properties prop = new Properties();
		
		prop.load(fis);
		
		System.out.println(prop.getProperty(browserdata));
		
		String browser = prop.getProperty(browserdata);
		
		return browser;
	}
}
