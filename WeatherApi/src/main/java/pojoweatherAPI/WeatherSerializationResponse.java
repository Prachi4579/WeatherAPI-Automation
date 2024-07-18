package pojoweatherAPI;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import pojo.Coordinates;


public class WeatherSerializationResponse {
	private Coordinates coord;
	 private List<Weather> weather; 
	 private String base;
    private MainWeather main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    
    @JsonIgnore 
    private Rain rain;

	private long dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;
    
    public Coordinates getCoord() {
		return coord;
	}
	public void setCoord(Coordinates coord) {
		this.coord = coord;
	}
	public List<Weather> getWeather() {
		return weather;
	}
	public void setWeather(List<Weather> weather) {
		this.weather =weather;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	@JsonIgnore 
    public Rain getRain() {
		return rain;
	}
	@JsonIgnore 
	public void setRain(Rain rain) {
		this.rain = rain;
	}
	public MainWeather getMain() {
		return main;
	}
	public void setMain(MainWeather main) {
		this.main = main;
	}
	public int getVisibility() {
		return visibility;
	}
	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	public Clouds getClouds() {
		return clouds;
	}
	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}
	public long getDt() {
		return dt;
	}
	public void setDt(long dt) {
		this.dt = dt;
	}
	public Sys getSys() {
		return sys;
	}
	public void setSys(Sys sys) {
		this.sys = sys;
	}
	public int getTimezone() {
		return timezone;
	}
	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	
}
