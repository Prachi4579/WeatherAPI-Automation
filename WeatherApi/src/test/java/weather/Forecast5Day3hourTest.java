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
import io.restassured.response.Response;
import report.ListenertestNG;

@Listeners(ListenertestNG.class)
public class Forecast5Day3hourTest extends Parameter {
	private static final Logger logger = LoggerFactory.getLogger(Forecast5Day3hourTest.class);

	@Test(testName = "Verify 5Days3hourForecast Endpoint returning 200 OK while sending request with GET method" , groups = {"TC-006","smoke","sanity"})
	public  void forecast_5Day3Hour() throws IOException {
		setupParams("TC-006");
		Response resp = request.get(getConfiguration("forecast_endpoint"));
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



	}


	@Test(testName = "Verify 5Days3hourForecast Endpoint returning 405 Method Not Found while sending request with PUT Method" , groups = {"TC-007","smoke","sanity"})
	public  void forecast_5Day3HourPUTMethod() {
		setupParams("TC-007");
		Response resp = request.put(getConfiguration("forecast_endpoint"));
		assertResponse(resp, 405);
	}

	@Test(testName = "5Day3Hour Forecast by Zip Code returning 200 OK while sending request with GET method" , groups = {"TC_012"})
	public  void forecast_5Day3HourByZipCode() {
		setupParams("TC-012");
		Response resp = request.get(getConfiguration("forecast_endpoint"));
		assertResponse(resp, 200);
		validateResponse(resp, "TC-012");
	}

	@Test(testName = "5Day3Hour Forecast by city ID returning 200 OK while sending request with GET method" , groups = {"TC_014"})
	public  void forecast_5Day3HourByCityID(){

		setupParams("TC-014");
		Response resp = request.get(getConfiguration("forecast_endpoint"));
		assertResponse(resp, 200);
		validateResponse(resp, "TC-014");
	}

	@Test(testName = "5Day3Hour Forecast by city Name returning 200 OK while sending request with GET method" , groups = {"TC_017"})
	public  void forecast_5Day3HourByCityName(){

		setupParams("TC-017");
		Response resp = request.get(getConfiguration("forecast_endpoint"));
		assertResponse(resp, 200);
		validateResponse(resp, "TC-017");
	}
}
