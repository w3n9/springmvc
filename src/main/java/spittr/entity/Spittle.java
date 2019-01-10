package spittr.entity;

import sun.security.provider.ConfigFile;

import java.util.Date;

public class Spittle {
	private int id;
	private String message;
	private Date time;
	private Double latitude;
	private Double longitude;
	@Override
	public String toString() {
		return "Spittle{" +
				"id=" + id +
				", message='" + message + '\'' +
				", time=" + time +
				", latitude=" + latitude +
				", longitude=" + longitude +
				'}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
