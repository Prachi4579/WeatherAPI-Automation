package weather;

import io.restassured.response.Response;
import report.ListenertestNG;
import java.io.IOException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ListenertestNG.class)
public class WeatherAPITest extends Parameter {

	@Test(testName = "Verify Current Weather GET-Endpoint returning 200 OK -valid API key" , groups = {"TC_001","smoke","sanity"})
	public  void currentWeatherValidKey(){

		setupParams("TC-001");
		Response resp = request.get(getConfiguration("weather_endpoint"));
		assertResponse(resp, 200);
	}

	@Test(testName = "Verify Current Weather GET-Endpoint returning 401 Unauthorized-Invalid key" , groups = {"TC-002","smoke","sanity"})
	public  void currentWeatherInValidKey() throws IOException{

		request.param("appid", "xyz");
		Response resp = request.get(getConfiguration("weather_endpoint"));
		assertResponse(resp, 401);

	}

	@Test(testName="Verify Current Weather GET-Endpoint returning 400 Bad Request-Invalid Latitude",groups= {"TC-003"})
	public  void currentWeatherInValidLatitude() throws IOException {
		setupParams("TC-003");
		Response resp = request.get(getConfiguration("weather_endpoint"));
		assertResponse(resp, 400);
	}


	@Test(testName = "Verify Current Weather GET-Endpoint returning 400 Bad Request-Invalid Longitude" , groups = {"TC-004","smoke","sanity"})
	public  void currentWeatherInValidLongitude() throws IOException {

		setupParams("TC-004");
		Response resp = request.get(getConfiguration("weather_endpoint"));
		assertResponse(resp, 400);

	}

	@Test(testName = "Verify Current Weather PUT-Endpoint returning 405 Method Not Allowed" , groups = {"TC-005","smoke","sanity"})
	public  void currentWeatherPUTMethod() throws IOException {

		setupParams("TC-005");
		Response resp = request.put(getConfiguration("weather_endpoint"));
		assertResponse(resp, 405);

	}
	@Test(testName = "Weather request by ZIP code returning 200 OK while sending request with GET method" , groups = {"TC_011"})
	public void weatherByZipCode() throws IOException {
		setupParams("TC-011");
		Response resp = request.get(getConfiguration("weather_endpoint"));
		assertResponse(resp, 200);

	}

	@Test(testName = "Weather request by city ID returning 200 OK while sending request with GET method" , groups = {"TC_013"})
	public  void weatherByCityID() {
		setupParams("TC-013");
		Response resp = request.get(getConfiguration("weather_endpoint"));
		assertResponse(resp,200);
	}

	@Test(testName = "Weather request by city name,country code returning 200 OK while sending request with GET method" , groups = {"TC_015"})
	public  void weatherByCityNameCountryCode(){
		setupParams("TC-015");
		Response resp = request.get(getConfiguration("weather_endpoint"));
		assertResponse(resp, 200);

	}

	@Test(testName = "Weather request by city name returning 200 OK while sending request with GET method" , groups = {"TC_016"})
	public  void weatherByCityName(){
		setupParams("TC-016");
		Response resp = request.get(getConfiguration("weather_endpoint"));
		assertResponse(resp, 200);

	}

}



