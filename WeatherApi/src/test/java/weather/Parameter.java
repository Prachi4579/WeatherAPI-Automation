package weather;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import utils.ExcelReaderUtils;
import utils.PropertiesReader;

public class Parameter extends ExcelReaderUtils{


	private static final Logger logger = LoggerFactory.getLogger(WeatherAPITest.class);
	RequestSpecification request;
	Map<String, Object> headerParam = new HashMap<String, Object>();
	String apiKey = PropertiesReader.getEndPoint().getProperty("apikey");





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
		test.createNode("Configuration").info("Fetching configuration for key: " + key);
		return testData.get(key).get("paramValue");
		
	}



	@BeforeClass
	public void goToURL() throws IOException {
		RestAssured.baseURI = "https://api.openweathermap.org";
		headerParam.put("Content-Type", "application/json; charset=utf-8");
		headerParam.put("Server", "openresty");

	}


	@BeforeMethod
	public void setup(Method method) {
		request = RestAssured.given();
		request.headers(headerParam);	
		test=extent.createTest(method.getName());
		

	}

	public void setupParams(String testcaseId) {
		request.param("appid", getConfiguration("appid"));
		request.params(getAPIWeatherTestData().get(testcaseId));
		test.info("Param added " + getAPIWeatherTestData().get(testcaseId));
		

	}

	public void assertResponse(Response res, int expected) {
		logger.info("Response code : " +res.getStatusCode());
		if(res.getStatusCode() ==  expected) {
			test.pass("Response code match , Actual : "+ res.getStatusCode() + " , expected : "+expected);
		}else {
			test.fail("Response code not match , Actual : "+ res.getStatusCode() + " , expected : "+expected);
		}
		Assert.assertEquals(res.getStatusCode(), expected);
	}


	static ExtentReports extent ;
	static ExtentTest test ;
	@BeforeSuite
	public static void suiteSetUp() {
		extent = new ExtentReports();
		
		DateTimeFormatter date=DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		LocalDateTime localDateTime=LocalDateTime.now();
		String formattedTime=date.format(localDateTime);
		
		String reportDirectory = System.getProperty("user.dir") + "/report/Report_" + formattedTime;

		String reportPath = reportDirectory + "/TestReport.html";
		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Weather Automation Report");
		spark.config().setReportName("Weather API Test Report");
		
		extent.attachReporter(spark);

	}

	@AfterSuite
	public static void suiteTearDown() {
		extent.flush();
	}




}  




