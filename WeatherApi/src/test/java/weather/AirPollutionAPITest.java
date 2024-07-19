package weather;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import pojo.GetAirPollutionData;
import report.ListenertestNG;

@Listeners(ListenertestNG.class)
public class AirPollutionAPITest extends Parameter {

	@Test(testName="Verify AirPollution Endpoint returning 200 OK while sending request with GET method", groups = {"TC-008","smoke", "sanity" })
	public void verify_airpollution_endpoint_200_valid_payload_GET_method() {
		setupParams("TC-008"); 
		Response resp = request.get(getConfiguration("airpollution_endpoint"));
		assertResponse(resp, 200);
		
				GetAirPollutionData getAirResponseData = resp.as(GetAirPollutionData.class);
				Assert.assertEquals(getAirResponseData.getCoord().getLat(),33.6025, "Latitudes matched");
				Assert.assertEquals(getAirResponseData.getCoord().getLon(),131.17, "Longitudes matched");
   
	}

	@Test(testName = "Verify AirPollution Endpoint returning Method not allowed 405 while sending request with PUT method", groups = {
			"TC-009", "smoke", "sanity" })
	public void verify_airpollution_endpoint_405_valid_payload_PUT_method() {
		setupParams("TC-009");
		Response resp = request.put(getConfiguration("airpollution_endpoint"));
		assertResponse(resp, 405);
	}

	@Test(testName = "Verify AirPollution Post method Data", groups = { "TC-01", "smoke", "sanity" }/*,retryAnalyzer = utils.APIRetry.class*/)
	public void verify_airpollution_endpoint_405_valid_payload_POST_method() {
		setupParams("TC-001");
		Response resp = request.post(getConfiguration("airpollution_endpoint"));
		assertResponse(resp, 405);
	}
}


