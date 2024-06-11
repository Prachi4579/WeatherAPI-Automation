package weather;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.PropertiesReader;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class WeatherAPITest extends Parameter {
	private static final Logger logger = LoggerFactory.getLogger(WeatherAPITest.class);


	@Test(testName = "Verify Current Weather Valid key" , groups = {"TC_001","smoke","sanity"})
	public  void currentWeatherValidKey(){

		RequestSpecification request = RestAssured.given();
		request.headers(headerParam);
		logger.debug("header : " + headerParam);
		request.param("appid", getConfiguration("appid"));
		request.params(getAPIWeatherTestData().get("TC-001"));
		//		logger.debug("param : " + param1);
		Response resp = request.get(getConfiguration("weather_endpoint"));
		logger.debug("response code : " + resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);

	}


	@Test(testName = "Verify Current Weather Invalid key" , groups = {"TC-002","smoke","sanity"})
	public  void currentWeatherInValidKey() throws IOException{

		//invalid key
		RequestSpecification request = RestAssured.given();
		request.headers(headerParam);
		logger.debug("header : " + headerParam);
		request.param("appid", "xyz");
		request.params(getAPIWeatherTestData().get("TC-002"));
		//		logger.debug("param : " + param1);
		Response resp = request.get(getConfiguration("weather_endpoint"));
		logger.debug("response code : " + resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 401);
	}
	

	@Test(testName="Verify Current Weather Invalid Latitude",groups= {"TC-003"})
	public  void currentWeatherInValidLatitude() throws IOException {

		RequestSpecification request = RestAssured.given();
		request.headers(headerParam);
		logger.debug("header : " + headerParam);
		request.param("appid", getConfiguration("appid"));
		request.params(getAPIWeatherTestData().get("TC-003"));
		//		logger.debug("param : " + param2);
		Response resp = request.get(getConfiguration("weather_endpoint"));
		logger.debug("response code : " + resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 400);
	}


	@Test(testName = "Verify Current Weather Invalid Longitude" , groups = {"TC-004","smoke","sanity"})
	public  void currentWeatherInValidLongitude() throws IOException {

		RequestSpecification request = RestAssured.given();
		request.headers(headerParam);
		logger.debug("header : " + headerParam);
		request.param("appid", getConfiguration("appid"));
		request.params(getAPIWeatherTestData().get("TC-004"));
		
		Response resp = request.get(getConfiguration("weather_endpoint"));
		logger.debug("response code : " + resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 400);

	}



	@Test(testName = "Verify Current Weather with PUT method" , groups = {"TC-005","smoke","sanity"})
	public  void currentWeatherPUTMethod() throws IOException {

		//put method
		RequestSpecification request = RestAssured.given();
		request.headers(headerParam);
		logger.debug("header : " + headerParam);

		request.param("appid", getConfiguration("appid"));
		request.params(getAPIWeatherTestData().get("TC-005"));
	
		Response resp = request.put(getConfiguration("weather_endpoint"));
		logger.debug("response code : " + resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 405);

	}

}



