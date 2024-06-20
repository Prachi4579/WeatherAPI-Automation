package weather;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import report.ListenertestNG;
import resources.ExtentReportNG;
import utils.ExcelReaderUtils;
import utils.PropertiesReader;

public class Parameter extends ExcelReaderUtils {

	private static final Logger logger = LoggerFactory.getLogger(WeatherAPITest.class);
	RequestSpecification request;
	Map<String, Object> headerParam = new HashMap<String, Object>();
	String apiKey = PropertiesReader.getEndPoint().getProperty("apikey");

	public Map<String, Map<String, String>> getAPIWeatherTestData() {
		String dataExcelPath = System.getProperty("user.dir") + "/src/test/resources/DataExcelRead.xlsx";
		String sheetName = "WeatherAPITestParameters";
		Map<String, Map<String, String>> testData = getWeatherAPIData(dataExcelPath, sheetName);
//		logger.debug("API Weather Test Data: {}", testData);
		return testData;
	}

	public String getConfiguration(String key) {
		String dataExcelPath = System.getProperty("user.dir") + "/src/test/resources/DataExcelRead.xlsx";
		String sheetName = "Configuration";
		Map<String, Map<String, String>> testData = getWeatherAPIData(dataExcelPath, sheetName);
//		logger.debug(sheetName);
		lg.test1.createNode("Configuration").info("Test Data : " + testData.get(key));
		return testData.get(key).get("paramValue");

	}

	@BeforeClass
	public void goToURL() throws IOException {
		RestAssured.baseURI = "https://api.openweathermap.org";
		headerParam.put("Content-Type", "application/json; charset=utf-8");
		headerParam.put("Server", "openresty");

	}

	static ListenertestNG lg = new ListenertestNG();


	@BeforeMethod
	public void setup(Method method) {
		request = RestAssured.given();
		request.headers(headerParam);
		lg.onTestStart(method.getName());
		lg.test1.getStatus();
//		lg.test1 = extent.createTest(method.getName());
		lg.test1.info("base URI " + RestAssured.baseURI);

	}

	public void setupParams(String testcaseId) {
		request.param("appid", getConfiguration("appid"));
		request.params(getAPIWeatherTestData().get(testcaseId));
		lg.test1.createNode("Request Param").info("Test Data : " + getAPIWeatherTestData().get(testcaseId));
		lg.test1.info("Param added " + getAPIWeatherTestData().get(testcaseId));

	}

	public static void assertResponse(Response res, int expected) {
//		logger.info("Response code : " +res.getStatusCode());
		if (res.getStatusCode() == expected) {
			lg.test1.pass("Response code match , Actual : " + res.getStatusCode() + " , expected : " + expected);
		} else {
			lg.test1.fail("Response code not match , Actual : " + res.getStatusCode() + " , expected : " + expected);
		}
		Assert.assertEquals(res.getStatusCode(), expected);
	}



	@BeforeSuite
	public static void suiteSetUp() {

		ExtentReportNG.getReportObject();

	}

	@AfterSuite
	public static void suiteTearDown() {
		ExtentReportNG ex = new ExtentReportNG();
		ex.extent.setSystemInfo("Tester", "Prachi Sharma");
		ex.extent.setSystemInfo("OS", "Windows11");
		ex.extent.flush();
		
		

	}

}
