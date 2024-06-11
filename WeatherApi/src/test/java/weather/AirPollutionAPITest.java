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

public class AirPollutionAPITest extends Parameter {
	
	private static final Logger logger = LoggerFactory.getLogger(AirPollutionAPITest.class);
	
	@Test(testName = "Verify AirPollution Data" , groups = {"TC-008","smoke","sanity"})
	public void verifyAirPollutionEndpoint() {
		RequestSpecification request = RestAssured.given();
		request.headers(headerParam);
		logger.debug("header : " + headerParam);
		request.param("appid", getConfiguration("appid"));
		request.params(getAPIWeatherTestData().get("TC-008"));
		Response resp = request.get(getConfiguration("airpollution_endpoint"));
		logger.debug("response code : " + resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);
		System.out.println(resp.prettyPrint());	
	}
	
	
	@Test(testName = "Verify AirPollution Put method Data" , groups = {"TC-009","smoke","sanity"})
	public void verifyPutCallAirPollutionEndpoint() {
		RequestSpecification request=RestAssured.given();
		request.headers(headerParam);
		logger.debug("header : " + headerParam);
		request.param("appid", getConfiguration("appid"));
		request.params(getAPIWeatherTestData().get("TC-009"));
		Response resp=request.put(getConfiguration("airpollution_endpoint"));
		logger.debug("response code : " + resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 405);
		System.out.println(resp.prettyPrint());	
	}
	
	
	@Test(testName = "Verify AirPollution Post method Data" , groups = {"TC-01","smoke","sanity"})
	public void verifyPostCallAirPollutionEndpoint() {
		RequestSpecification request=RestAssured.given();
		request.headers(headerParam);
//		logger.debug("header : " + headerParam);
		request.param("appid", getConfiguration("appid"));
		request.params(getAPIWeatherTestData().get("TC-02"));
		Response resp=request.post(getConfiguration("airpollution_endpoint"));
		
//		logger.debug("response code : " + resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 405);
		
		
//		given().log().all().queryParam("appid", "7e8b32da3782f0b3660cff9192acfdec").headers(headerParam).when().post(getConfiguration("airpollution_endpoint")).then().log().all().assertThat().statusCode(405);
//		
		
	}
	
}
	

