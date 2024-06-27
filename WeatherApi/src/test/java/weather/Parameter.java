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


	public Map<String, Map<String, String>> getAPIWeatherTestData() {
		String dataExcelPath = System.getProperty("user.dir") + "/src/test/resources/DataExcelRead.xlsx";
		String sheetName = "WeatherAPITestParameters";
		Map<String, Map<String, String>> testData = getWeatherAPIData(dataExcelPath, sheetName);
		//		logger.debug("API Weather Test Data: {}", testData);
		return testData;
	}
	public Map<String, Map<String, String>> getZipParameters() {
		String dataExcelPath = System.getProperty("user.dir") + "/src/test/resources/DataExcelRead.xlsx";
		String sheetName = "ZipParameters";
		Map<String, Map<String, String>> testData1 = getWeatherAPIData(dataExcelPath, sheetName);
//		logger.debug("API Weather Test Data: {}", testData1);
		return testData1;
	}
	public Map<String, Map<String, String>> getCityIDParameters() {
		String dataExcelPath = System.getProperty("user.dir") + "/src/test/resources/DataExcelRead.xlsx";
		String sheetName = "CityIDParameters";
		Map<String, Map<String, String>> testData2 = getWeatherAPIData(dataExcelPath, sheetName);
//		logger.debug("API Weather Test Data: {}", testData2);
		return testData2;
	}
	public Map<String, Map<String, String>> getCityNameCountryParameters() {
		String dataExcelPath = System.getProperty("user.dir") + "/src/test/resources/DataExcelRead.xlsx";
		String sheetName = "CityNameCountryParameters";
		Map<String, Map<String, String>> testData3 = getWeatherAPIData(dataExcelPath, sheetName);
//		logger.debug("API Weather Test Data: {}", testData3);
		return testData3;
	}

	public String getConfiguration(String key) {
		String dataExcelPath = System.getProperty("user.dir") + "/src/test/resources/DataExcelRead.xlsx";
		String sheetName = "Configuration";
		Map<String, Map<String, String>> testData4 = getWeatherAPIData(dataExcelPath, sheetName);
		//		logger.debug(sheetName);
		
		return testData4.get(key).get("paramValue");

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
		lg.test1.info("base URI " + RestAssured.baseURI);

	}

	public void setupParams(String testcaseId) {
		request.param("appid", getConfiguration("appid"));
		switch (testcaseId) {
		case "TC-001":
		case "TC-002":
		case "TC-003":
		case "TC-004":
		case "TC-005":
		case "TC-006":
		case "TC-007":
		case "TC-008":
		case "TC-009":
		case "TC-010":
			request.params(getAPIWeatherTestData().get(testcaseId));
			lg.test1.info("Param added " + getAPIWeatherTestData().get(testcaseId));
			break;

		case "TC-011":
		case "TC-012":
			request.params(getZipParameters().get(testcaseId));
			lg.test1.info("Param added " + getZipParameters().get(testcaseId));

			break;

		case "TC-013":
		case "TC-014":
			request.params(getCityIDParameters().get(testcaseId));
			lg.test1.info("Param added " + getCityIDParameters().get(testcaseId));

			break;

		case "TC-015":
		case "TC-016":
		case "TC-017":
			request.params(getCityNameCountryParameters().get(testcaseId));
			lg.test1.info("Param added " + getCityNameCountryParameters().get(testcaseId));
			break;

		default:
			logger.error("Invalid testcaseId: {}", testcaseId);
			throw new IllegalArgumentException("Invalid testcaseId: " + testcaseId);
		}

//		lg.test1.createNode("Request Param").info("Test Data : " + getAPIWeatherTestData().get(testcaseId));
		

//		lg.test1.createNode("Request Param").info("Test Data : " + getZipParameters().get(testcaseId));
		
//		lg.test1.createNode("Request Param").info("Test Data : " + getCityIDParameters().get(testcaseId));
		

//		lg.test1.createNode("Request Param").info("Test Data : " + getCityNameCountryParameters().get(testcaseId));
		
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
