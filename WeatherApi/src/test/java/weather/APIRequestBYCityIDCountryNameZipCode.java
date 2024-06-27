package weather;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class APIRequestBYCityIDCountryNameZipCode extends Parameter {
	
	@Test(testName = "Weather request by ZIP code" , groups = {"TC_011"})
	public  void weatherByZipCode() throws IOException {
		setupParams("TC-011");
		Response resp = request.get(getConfiguration("weather_endpoint"));
		assertResponse(resp, 200);

	}

	@Test(testName = "5Day3Hour Forecast by Zip Code" , groups = {"TC_012"})
	public  void forecast_5Day3HourByZipCode() {
		setupParams("TC-012");
		Response resp = request.get(getConfiguration("forecast_endpoint"));
		assertResponse(resp, 200);
	}

	@Test(testName = "Weather request by city ID" , groups = {"TC_013"})
	public  void weatherByCityID() {
		setupParams("TC-013");
		Response resp = request.get(getConfiguration("weather_endpoint"));
		assertResponse(resp,200);
	}
	@Test(testName = "5Day3Hour Forecast by city ID" , groups = {"TC_014"})
	public  void forecast_5Day3HourByCityID(){

		setupParams("TC-014");
		Response resp = request.get(getConfiguration("forecast_endpoint"));
		assertResponse(resp, 200);
		//Assert.assertEquals("The response code is " + resp, "200");
	}
	@Test(testName = "Weather request by city name,country code" , groups = {"TC_015"})
	public  void weatherByCityNameCountryCode(){

		setupParams("TC-015");
		Response resp = request.get(getConfiguration("weather_endpoint"));
		assertResponse(resp, 200);
		//Assert.assertEquals("The response code is " + resp, "200");
	}
	
	@Test(testName = "Weather request by city name" , groups = {"TC_016"})
	public  void weatherByCityName(){

		setupParams("TC-016");
		Response resp = request.get(getConfiguration("weather_endpoint"));
		assertResponse(resp, 200);
		//Assert.assertEquals("The response code is " + resp, "200");
	}
	@Test(testName = "5Day3Hour Forecast by city Name" , groups = {"TC_017"})
	public  void forecast_5Day3HourByCityName(){

		setupParams("TC-017");
		Response resp = request.get(getConfiguration("forecast_endpoint"));
		assertResponse(resp, 200);
		//Assert.assertEquals("The response code is " + resp, "200");
	}
}
//
