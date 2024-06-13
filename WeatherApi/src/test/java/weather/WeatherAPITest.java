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

		setupParams("TC-001");
		Response resp = request.get(getConfiguration("weather_endpoint"));
		assertResponse(resp, 200);
	}


	@Test(testName = "Verify Current Weather Invalid key" , groups = {"TC-002","smoke","sanity"})
	public  void currentWeatherInValidKey() throws IOException{

		//invalid key

		request.param("appid", "xyz");
		request.params(getAPIWeatherTestData().get("TC-002"));

		Response resp = request.get(getConfiguration("weather_endpoint"));
		assertResponse(resp, 401);
	}


	@Test(testName="Verify Current Weather Invalid Latitude",groups= {"TC-003"})
	public  void currentWeatherInValidLatitude() throws IOException {
		setupParams("TC-003");
		Response resp = request.get(getConfiguration("weather_endpoint"));
		assertResponse(resp, 400);
	}


	@Test(testName = "Verify Current Weather Invalid Longitude" , groups = {"TC-004","smoke","sanity"})
	public  void currentWeatherInValidLongitude() throws IOException {

		setupParams("TC-004");
		Response resp = request.get(getConfiguration("weather_endpoint"));
		assertResponse(resp, 400);

	}



	@Test(testName = "Verify Current Weather with PUT method" , groups = {"TC-005","smoke","sanity"})
	public  void currentWeatherPUTMethod() throws IOException {

		setupParams("TC-005");
		Response resp = request.put(getConfiguration("weather_endpoint"));
		assertResponse(resp, 405);

	}

}



