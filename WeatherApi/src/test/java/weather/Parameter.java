package weather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import utils.ExcelReaderUtils;
import utils.PropertiesReader;

public class Parameter extends ExcelReaderUtils{
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherAPITest.class);
	public Map<String,Map<String,String>> getAPIWeatherTestData() {
		String dataExcelPath = System.getProperty("user.dir")+"/src/test/resources/DataExcelRead.xlsx";
		String sheetName = "WeatherAPITestParameters";
		Map<String,Map<String,String>> testData = getWeatherAPIData(dataExcelPath, sheetName);
		logger.debug("API Weather Test Data: {}", testData);
		return testData;
	}
	public String getConfiguration(String key) {
		String dataExcelPath = System.getProperty("user.dir")+"/src/test/resources/DataExcelRead.xlsx";
		String sheetName = "Configuration";
		Map<String,Map<String,String>> testData = getWeatherAPIData(dataExcelPath, sheetName);
		logger.debug(sheetName);
		return testData.get(key).get("paramValue");
	}
	
	
	Map<String, Object> headerParam = new HashMap<String, Object>();
	String apiKey = PropertiesReader.getEndPoint().getProperty("apikey");


	    @BeforeClass
	    public void goToURL() throws IOException {
	        RestAssured.baseURI = "https://api.openweathermap.org";
	        headerParam.put("Content-Type", "application/json; charset=utf-8");
	        headerParam.put("Server", "openresty");
	      
	    }

	}  

	    
	

