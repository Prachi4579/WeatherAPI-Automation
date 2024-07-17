package weather;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import pojo.Components;
import pojo.GetAirPollutionData;
import report.ListenertestNG;

@Listeners(ListenertestNG.class)
public class AirPollutionAPITest extends Parameter {

	@Test(testName="Verify AirPollution Endpoint returning 200 OK while sending request with GET method", groups = {"TC-008","smoke", "sanity" })
	public void verifyAirPollutionEndpoint() {
		setupParams("TC-008"); 
		Response resp = request.get(getConfiguration("airpollution_endpoint"));
		assertResponse(resp, 200);
		
		
		
		
//		GetAirPollutionData getAirResponseData = resp.as(GetAirPollutionData.class);
//        // Validation with lat, log
//        System.out.println("Latitude: " + getAirResponseData.getCoord().getLat());
//        System.out.println("Number of entries in the list: " + getAirResponseData.getList().size());
//
//        for (pojo.List item : getAirResponseData.getList()) {
//            Components components = item.getComponents();
//            System.out.println("AQI: " + item.getMain().getAqi());
//            System.out.println("CO: " + components.getCo());
//            System.out.println("NO: " + components.getNo());
//            System.out.println("NO2: " + components.getNo2());
//            System.out.println("O3: " + components.getO3());
//            System.out.println("SO2: " + components.getSo2());
//            System.out.println("PM2.5: " + components.getPm2_5());
//            System.out.println("PM10: " + components.getPm10());
//            System.out.println("NH3: " + components.getNh3());
//            System.out.println(".........");
//        }
	}

	@Test(testName = "Verify AirPollution Endpoint returning Method not allowed 405 while sending request with PUT method", groups = {
			"TC-009", "smoke", "sanity" })
	public void verifyPutCallAirPollutionEndpoint() {
		setupParams("TC-009");
		Response resp = request.put(getConfiguration("airpollution_endpoint"));
		assertResponse(resp, 405);
	}

	@Test(testName = "Verify AirPollution Post method Data", groups = { "TC-01", "smoke", "sanity" }/*,retryAnalyzer = utils.APIRetry.class*/)
	public void verifyPostCallAirPollutionEndpoint() {
		setupParams("TC-001");
		Response resp = request.post(getConfiguration("airpollution_endpoint"));
		assertResponse(resp, 405);
	}
}


