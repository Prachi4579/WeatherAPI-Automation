package forecastpojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pojo.Coordinates;

@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
    private int id;
	    private String name;
	    private Coordinates coord;

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

	    public Coordinates getCoord() {
	        return coord;
	    }

	    public void setCoord(Coordinates coord) {
	        this.coord = coord;
	    }
	}

