package pojoweatherAPI;

import java.util.Date;

public class Sys {

	private Integer type;
	private Integer id;
	private String country;
	private Date sunrise;
	private Date sunset;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Date getSunrise() {
		return sunrise;
	}
	public void setSunrise(Date sunrise) {
		this.sunrise = sunrise;
	}
	public Date getSunset() {
		return sunset;
	}
	public void setSunset(Date sunset) {
		this.sunset = sunset;
	}
	
	
	
}
