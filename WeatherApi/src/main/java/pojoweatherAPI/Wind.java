package pojoweatherAPI;

public class Wind {
	private double speed;
	private int deg;
	private int gust;

	public int getGust() {
		return gust;
	}

	public void setGust(int gust) {
		this.gust = gust;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getDeg() {
		return deg;
	}

	public void setDeg(int deg) {
		this.deg = deg;
	}
}
