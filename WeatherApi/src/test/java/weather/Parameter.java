package weather;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import report.ListenertestNG;
import resources.ExtentReportNG;
import utils.ExcelReaderUtils;

public class Parameter extends ExcelReaderUtils {

	RequestSpecification request;
	Map<String, Object> headerParam = new HashMap<String, Object>();
	Map<String, Map<String, String>> testData = null;


	public Map<String, Map<String, String>> loadTestdata() {
		testData = new HashMap<String, Map<String, String>>();
		String dataExcelPath = System.getProperty("user.dir") + "/src/test/resources/DataExcelRead.xlsx";
		Map<String, Map<String, String>> wtestData = getWeatherAPIData(dataExcelPath, "WeatherAPITestParameters");
		testData.putAll(wtestData);
		wtestData = getWeatherAPIData(dataExcelPath, "ZipParameters");
		testData.putAll(wtestData);
		wtestData = getWeatherAPIData(dataExcelPath, "CityIDParameters");
		testData.putAll(wtestData);
		wtestData = getWeatherAPIData(dataExcelPath, "CityNameCountryParameters");
		testData.putAll(wtestData);

		return testData;
	}

	public String getConfiguration(String key) {
		String dataExcelPath = System.getProperty("user.dir") + "/src/test/resources/DataExcelRead.xlsx";
		String sheetName = "Configuration";
		Map<String, Map<String, String>> testData = getWeatherAPIData(dataExcelPath, sheetName);
		if(!key.equals("appid")) {
			lg.test1.info("Target Resource - " + RestAssured.baseURI+testData.get(key).get("paramValue"));
		}
		return testData.get(key).get("paramValue");

	}

	@BeforeClass
	public void goToURL() throws IOException {
		RestAssured.baseURI = "https://api.openweathermap.org";
		headerParam.put("Content-Type", "application/json; charset=utf-8");
		headerParam.put("Server", "openresty");
		loadTestdata();
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
		request.params(testData.get(testcaseId));
		lg.test1.info("Param added " +testData.get(testcaseId));
	}

	public static void assertResponse(Response res, int expected) {
		
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
