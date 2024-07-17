package pojo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class AirPollutionDeserialize {
	public static void main(String[] args) {


		RestAssured.baseURI= "http://api.openweathermap.org";
		String response=given().log().all().queryParams("appid", "7e8b32da3782f0b3660cff9192acfdec").queryParams("lat", "33.60247")
				.queryParams("lon", "131.17").header("Content-Type","application/json; charset=utf-8")
				.when().get("/data/2.5/air_pollution")
				.then().assertThat().statusCode(200).extract().response().asString();

		JsonPath jsonPathAirPollution=new JsonPath(response);

		GetAirPollutionData gc=given().queryParams("appid", "7e8b32da3782f0b3660cff9192acfdec").queryParams("lat", "33.60247")
				.queryParams("lon", "131.17").header("Content-Type","application/json; charset=utf-8").when().log().all().get("/data/2.5/air_pollution").as(GetAirPollutionData.class);

		System.out.println(gc.getCoord().getLat());
		System.out.println("aqi....."+gc.getList().size());
		for (pojo.List item : gc.getList()) {
			Components components = item.getComponents();
			System.out.println("AQI: " + item.getMain().getAqi());
			System.out.println("CO: " + components.getCo());
			System.out.println("NO: " + components.getNo());
			System.out.println("NO2: " + components.getNo2());
			System.out.println("O3: " + components.getO3());
			System.out.println("SO2: " + components.getSo2());
			System.out.println("PM2.5: " + components.getPm2_5());
			System.out.println("PM10: " + components.getPm10());
			System.out.println("NH3: " + components.getNh3());
			System.out.println("-----------------------------");
		}

	}
}
