package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {
public static Properties getEndPoint() {
	try {
		Properties prop=new Properties();
		prop.load(new FileInputStream(new File(System.getProperty("user.dir")+"/config.properties")));
		System.out.println(prop);
		return prop;
		
	} catch (Exception e) {
		return null;
	}
}
}
