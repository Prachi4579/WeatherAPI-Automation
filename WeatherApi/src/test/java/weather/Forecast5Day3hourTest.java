package weather;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import utils.PropertiesReader;

public class Forecast5Day3hourTest extends Parameter {

	private static final Logger logger = LoggerFactory.getLogger(Forecast5Day3hourTest.class);
	@Test(testName = "Verify 5Days3hourForecast Data" , groups = {"TC-006","smoke","sanity"})
	public  void forecast_5Day3Hour() throws IOException {

		setupParams("TC-006");
		Response resp = request.get(getConfiguration("forecast_endpoint"));
		assertResponse(resp, 200);

	}

	@Test(testName = "Verify 5Days3hourForecast PUT Method Data" , groups = {"TC-007","smoke","sanity"})
	public  void forecast_5Day3HourPUTMethod() {


		setupParams("TC-007");
		Response resp = request.put(getConfiguration("forecast_endpoint"));
		assertResponse(resp, 405);
	}

}
