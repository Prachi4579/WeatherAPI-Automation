package pojoweatherAPI;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Rain {
	 @JsonProperty("1h")
private double Onehr;

public double getOnehr() {
	return Onehr;
}

public void setOnehr(double onehr) {
	Onehr = onehr;
}
}
