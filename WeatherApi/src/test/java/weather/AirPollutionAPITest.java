package weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import report.ListenertestNG;

@Listeners(ListenertestNG.class)
public class AirPollutionAPITest extends Parameter {

	private static final Logger logger = LoggerFactory.getLogger(AirPollutionAPITest.class);

	//Verify AirPollution Endpoint returning 200 OK while sending request with GET method
	@Test(testName="Verify AirPollution Endpoint returning 200 OK while sending request with GET method", groups = {"TC-008","smoke", "sanity" })
	public void verifyAirPollutionEndpoint() {
		setupParams("TC-008"); 
		Response resp = request.get(getConfiguration("airpollution_endpoint"));
		assertResponse(resp, 200);

	}

	@Test(testName = "Verify AirPollution Endpoint returning Method not allowed 405 while sending request with PUT method", groups = {
			"TC-009", "smoke", "sanity" })
	public void verifyPutCallAirPollutionEndpoint() {
		setupParams("TC-009");
		Response resp = request.put(getConfiguration("airpollution_endpoint"));
		assertResponse(resp, 405);
	}

	@Test(testName = "Verify AirPollution Post method Data", groups = { "TC-01", "smoke", "sanity" })
	public void verifyPostCallAirPollutionEndpoint() {
		setupParams("TC-001");
		Response resp = request.post(getConfiguration("airpollution_endpoint"));
		assertResponse(resp, 405);
	}

}
	

