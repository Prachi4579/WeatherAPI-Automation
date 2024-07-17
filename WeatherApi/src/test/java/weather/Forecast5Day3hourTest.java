package weather;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.ss.formula.functions.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import forecastpojo.City;
import io.restassured.response.Response;
import pojoweatherAPI.WeatherSerializationResponse;
import report.ListenertestNG;

@Listeners(ListenertestNG.class)
public class Forecast5Day3hourTest extends Parameter {
	private static final Logger logger = LoggerFactory.getLogger(Forecast5Day3hourTest.class);
	Response resp;

	public void forecastDeserialization() {
		City city=resp.jsonPath().getObject("city", City.class); 
		logger.info("id:"+city.getId());
		logger.info("name: " + city.getName());
		logger.info("latitude: " + city.getCoord().getLat());
		logger.info("longitude: " + city.getCoord().getLon());

	}

	@Test(testName = "Verify 5Days3hourForecast Endpoint returning 200 OK while sending request with GET method" , groups = {"TC-006","smoke","sanity"})
	public  void forecast_5Day3Hour() throws IOException {
		setupParams("TC-006");
		resp = request.get(getConfiguration("forecast_endpoint"));
		assertResponse(resp, 200);

		ArrayList<String> listOfDates=resp.jsonPath().get("list.dt_txt");
		int lengthOfList=listOfDates.size();
		System.out.println(listOfDates);

		LocalDateTime firstDateInResult = LocalDateTime.parse( listOfDates.get(0),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime LastDateInResult = LocalDateTime.parse( listOfDates.get(lengthOfList-1),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		System.out.println(LastDateInResult+"-------"+firstDateInResult);

		LocalDate firstDate = firstDateInResult.toLocalDate();
		LocalDate lastDate = LastDateInResult.toLocalDate();

		long daysBetween = ChronoUnit.DAYS.between(firstDate, lastDate);
		assertEquals(daysBetween, 5);
		validateResponse(resp, "TC-006");
		forecastDeserialization();
	}


	@Test(testName = "Verify 5Days3hourForecast Endpoint returning 405 Method Not Found while sending request with PUT Method" , groups = {"TC-007","smoke","sanity"})
	public  void forecast_5Day3HourPUTMethod() {
		setupParams("TC-007");
		resp = request.put(getConfiguration("forecast_endpoint"));
		assertResponse(resp, 405);
	}

	@Test(testName = "5Day3Hour Forecast by Zip Code returning 200 OK while sending request with GET method" , groups = {"TC_012"})
	public  void forecast_5Day3HourByZipCode() {
		setupParams("TC-012");
		resp = request.get(getConfiguration("forecast_endpoint"));
		assertResponse(resp, 200);
		validateResponse(resp, "TC-012");
		forecastDeserialization();
	}

	@Test(testName = "5Day3Hour Forecast by city ID returning 200 OK while sending request with GET method" , groups = {"TC_014"})
	public  void forecast_5Day3HourByCityID(){

		setupParams("TC-014");
		resp = request.get(getConfiguration("forecast_endpoint"));
		assertResponse(resp, 200);
		validateResponse(resp, "TC-014");
		forecastDeserialization();
	}

	@Test(testName = "5Day3Hour Forecast by city Name returning 200 OK while sending request with GET method" , groups = {"TC_017"})
	public  void forecast_5Day3HourByCityName(){

		setupParams("TC-017");
		resp = request.get(getConfiguration("forecast_endpoint"));
		assertResponse(resp, 200);
		validateResponse(resp, "TC-017");
		forecastDeserialization();





	}
}
