package weather;

import java.io.IOException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import report.ListenertestNG;

@Listeners(ListenertestNG.class)
public class Forecast5Day3hourTest extends Parameter {

	@Test(testName = "Verify 5Days3hourForecast Endpoint returning 200 OK while sending request with GET method" , groups = {"TC-006","smoke","sanity"})
	public  void forecast_5Day3Hour() throws IOException {
		setupParams("TC-006");
		Response resp = request.get(getConfiguration("forecast_endpoint"));
		assertResponse(resp, 200);

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
	}

	@Test(testName = "5Day3Hour Forecast by city ID returning 200 OK while sending request with GET method" , groups = {"TC_014"})
	public  void forecast_5Day3HourByCityID(){

		setupParams("TC-014");
		Response resp = request.get(getConfiguration("forecast_endpoint"));
		assertResponse(resp, 200);
			}
	
	@Test(testName = "5Day3Hour Forecast by city Name returning 200 OK while sending request with GET method" , groups = {"TC_017"})
	public  void forecast_5Day3HourByCityName(){

		setupParams("TC-017");
		Response resp = request.get(getConfiguration("forecast_endpoint"));
		assertResponse(resp, 200);
	}
}
