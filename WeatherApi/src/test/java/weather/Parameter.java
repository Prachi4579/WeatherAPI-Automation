package weather;

import static org.testng.Assert.assertEquals;
import forecastpojo.City;
import pojo.Coordinates;
import pojoweatherAPI.Sys;
import pojoweatherAPI.Weather;
import pojoweatherAPI.WeatherSerializationResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import forecastpojo.City;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.Coordinates;
import pojoweatherAPI.Sys;
import pojoweatherAPI.WeatherSerializationResponse;
import report.ListenertestNG;
import resources.ExtentReportNG;
import utils.ExcelReaderUtils;
import utils.PropertiesReader;

public class Parameter extends ExcelReaderUtils  {
	private static final Logger logger = LoggerFactory.getLogger(Parameter.class);
	static Properties properties= PropertiesReader.getEndPoint();
	RequestSpecification request;
	Map<String, Object> headerParam = new HashMap<String, Object>();
	Map<String, Map<String, String>> testData = null;
	static Map<String, Map<String, String>> testData1 = null;

	public Map<String, Map<String, String>> loadTestdata() {
		testData = new HashMap<String, Map<String, String>>();
		String dataExcelPath = System.getProperty("user.dir") + "/src/test/resources/DataExcelRead.xlsx";
		logger.info("Data Excel path: {}", dataExcelPath);

		Map<String, Map<String, String>> sheetTestData = getWeatherAPIData(dataExcelPath, "WeatherAPITestParameters");
		testData.putAll(sheetTestData);
		logger.info("Loaded WeatherAPITestParameters data: {}", sheetTestData);

		sheetTestData = getWeatherAPIData(dataExcelPath, "ZipParameters");
		testData.putAll(sheetTestData);
		logger.info("Loaded ZipParameters data: {}", sheetTestData);

		sheetTestData = getWeatherAPIData(dataExcelPath, "CityIDParameters");
		testData.putAll(sheetTestData);
		logger.info("Loaded CityIDParameters data: {}", sheetTestData);

		sheetTestData = getWeatherAPIData(dataExcelPath, "CityNameCountryParameters");
		testData.putAll(sheetTestData);
		logger.trace("Loaded CityNameCountryParameters data: {}", sheetTestData);


		logger.debug("API Weather Test Data: {}", testData);
		return testData;
	}

	public  String getConfiguration(String key) {
		logger.trace("Getting key: {}", key);
		String dataExcelPath = System.getProperty("user.dir") + "/src/test/resources/DataExcelRead.xlsx";
		String sheetName = "Configuration";
		logger.trace("Configuration sheet name: {}", sheetName);

		Map<String, Map<String, String>> testData = getWeatherAPIData(dataExcelPath, sheetName);
		if(!key.equals("appid")) {
			logger.trace("Key is not appid: {}", key);
		}
		return testData.get(key).get("paramValue");
	}

	@BeforeClass
	public void goToURL() throws IOException {
		logger.trace("Setting up base URI and headers.");
		RestAssured.baseURI = "https://api.openweathermap.org";
		headerParam.put("Content-Type", "application/json; charset=utf-8");
		headerParam.put("Server", "openresty");
		loadTestdata();
	}

	static ListenertestNG lg = new ListenertestNG();

	@BeforeMethod
	public void setup(Method method) {
		logger.trace("Setting up request for method: {}", method.getName());
		request = RestAssured.given();
		request.headers(headerParam);
		lg.onTestStart(method.getName());
		lg.test1.getStatus();
		lg.test1.info("base URI " + RestAssured.baseURI);
	}

	public void setupParams(String testcaseId) {
		logger.trace("Setting up parameters for test case ID: {}", testcaseId);
		request.param("appid", getConfiguration("appid"));
		request.params(testData.get(testcaseId));
		lg.test1.info("Param added " +testData.get(testcaseId));
	}

	public static void assertResponse(Response res, int expected) {
		logger.trace("Asserting response. Expected: {}, Actual: {}", expected, res.getStatusCode());
		if (res.getStatusCode() == expected) {
			lg.test1.pass("Response code match , Actual : " + res.getStatusCode() + " , expected : " + expected);
		} else {
			lg.test1.fail("Response code not match , Actual : " + res.getStatusCode() + " , expected : " + expected);
		}
		Assert.assertEquals(res.getStatusCode(), expected);
	}

	public static void validateResponse(Response resp,String testCaseId) {

		Map<String, Map<String, String>> responseParametersWithData = new HashMap<>();
		String dataExcelPath = System.getProperty("user.dir") + "/src/test/resources/DataExcelRead.xlsx";

		Map<String, Map<String, String>> forecastData = getWeatherAPIData(dataExcelPath, "ForecastValidations");
		responseParametersWithData.putAll(forecastData);

		Map<String, Map<String, String>> currentWeatherData = getWeatherAPIData(dataExcelPath, "CurrentWeatherValidations");
		responseParametersWithData.putAll(currentWeatherData);
		Map<String, String> expectedValues = responseParametersWithData.get(testCaseId);

		if (expectedValues != null) {
			for (Map.Entry<String, String> entry : expectedValues.entrySet()) {
				String key = entry.getKey();
				String expectedValue = entry.getValue();

				if (!key.equals("Identifier") && expectedValue != null && !expectedValue.isEmpty()) { 
					String actualValue = resp.jsonPath().getString(key);
					assertEquals(actualValue, expectedValue, "Mismatch for key: " + key);
					lg.test1.pass("Match for key: " + key + " - Expected: " + expectedValue + ", Actual: " + actualValue);
					logger.info("Match for key: {} - Expected: {}, Actual: {}", key, expectedValue, actualValue);
				}
			}
		} else {
			System.out.println("Test case ID " + testCaseId + " not found in the test data.");

			lg.test1.fail("Mismatch for key: ");
		}
	}

	public static void deserializationDataAssertion(Response resp,String testCaseId) {

		String dataExcelPath = System.getProperty("user.dir") + "/src/test/resources/DataExcelRead.xlsx";
		Map<String, Map<String, String>> dataForValidation = getWeatherAPIData(dataExcelPath, "CombinedDataDeserialization");
		logger.debug("Data for validation: {}", dataForValidation);

		Map<String, String> expectedValues = dataForValidation.get(testCaseId);
		if (expectedValues == null) {
			logger.error("No data found for test case ID: {}", testCaseId);
			throw new IllegalArgumentException("No data found for test case ID: " + testCaseId);
		}

		WeatherSerializationResponse weatherResponse = resp.jsonPath().getObject("", WeatherSerializationResponse.class);
		Sys sys = weatherResponse.getSys();
		Coordinates coord = weatherResponse.getCoord();

		assertEquals(sys.getCountry(), expectedValues.get("Country"));
		lg.test1.pass(" Expected Country : " + expectedValues.get("Country") + ", Actual Country: " + sys.getCountry());

		assertEquals(String.valueOf(coord.getLat()), expectedValues.get("lat"));
		lg.test1.pass(" - Expected Latitude: " + expectedValues.get("lat") + ", Actual Latitude: " + String.valueOf(coord.getLat()));

		assertEquals(String.valueOf(coord.getLon()), expectedValues.get("lon"));
		lg.test1.pass(" - Expected Longitude: " +  expectedValues.get("lon") + ", Actual Longitude: " + String.valueOf(coord.getLon()));

		assertEquals(String.valueOf(weatherResponse.getTimezone()), expectedValues.get("Timezone"));
		lg.test1.pass(" - Expected Timezone: " + expectedValues.get("Timezone") + ", Actual Timezone: " + String.valueOf(weatherResponse.getTimezone()));

		assertEquals(String.valueOf(weatherResponse.getId()), expectedValues.get("id"));
		lg.test1.pass(" - Expected CityID: " + expectedValues.get("id") + ", Actual CityID: " +String.valueOf(weatherResponse.getId()));

		assertEquals(weatherResponse.getName(), expectedValues.get("City_name"));
		lg.test1.pass(" - Expected City name: " + expectedValues.get("City_name") + ", Actual City name: " + weatherResponse.getName());
	}
	//---------------------------------------------------------------------------------------------------------------/  

	public static void deserializationForecastAssertion(Response resp,String testCaseId) {

		String dataExcelPath = System.getProperty("user.dir") + "/src/test/resources/DataExcelRead.xlsx";
		Map<String, Map<String, String>> dataForValidation = getWeatherAPIData(dataExcelPath, "CombinedDataDeserialization");
		logger.debug("Data for validation: {}", dataForValidation);

		Map<String, String> expectedValues = dataForValidation.get(testCaseId);
		if (expectedValues == null) {
			logger.error("No data found for test case ID: {}", testCaseId);
			throw new IllegalArgumentException("No data found for test case ID: " + testCaseId);
		}

		City city=resp.jsonPath().getObject("city", City.class);
		assertEquals(city.getCountry(), expectedValues.get("Country"));
		lg.test1.pass(" - Expected Country : " + expectedValues.get("Country") + ", Actual Country: " + city.getCountry());

		assertEquals(String.valueOf(city.getCoord().getLat()), expectedValues.get("lat"));
		lg.test1.pass(" - Expected Latitude: " + expectedValues.get("lat") + ", Actual Latitude: " + String.valueOf(city.getCoord().getLat()));

		assertEquals(String.valueOf(city.getCoord().getLon()), expectedValues.get("lon"));
		lg.test1.pass(" - Expected Longitude: " +  expectedValues.get("lon") + ", Actual Longitude: " + String.valueOf(city.getCoord().getLon()));

		assertEquals(String.valueOf(city.getTimezone()), expectedValues.get("Timezone"));
		lg.test1.pass(" - Expected Timezone: " + expectedValues.get("Timezone") + ", Actual Timezone: " + String.valueOf(city.getTimezone()));

		assertEquals(String.valueOf(city.getId()), expectedValues.get("id"));
		lg.test1.pass(" - Expected CityID: " + expectedValues.get("id") + ", Actual CityID: " +String.valueOf(city.getId()));
	}

	@BeforeSuite
	public static void suiteSetUp() {
		ExtentReportNG.getReportObject();
	}

	@AfterSuite
	public static void suiteTearDown() {
		ExtentReportNG ex = new ExtentReportNG();
		ex.extent.setSystemInfo(properties.getProperty("systeminfokey_1"),properties.getProperty( "systeminfovalue_1"));
		ex.extent.setSystemInfo(properties.getProperty("systeminfokey_2"),properties.getProperty( "systeminfovalue_2"));
		ex.extent.flush();
	}
	
}
