package com.djedra.WeatherApp.repo.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Weather {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String city;
	private Double temperature;
	private LocalDate dateOfRequest;
	private LocalTime timeOfRequest;
	private LocalDate dateOfMeasuring;
	private LocalTime timeOfMeasuring;
	private Double windSpeed;
	private String windDirection;
	private Double airPressure;
	private String weatherState;
	private Integer humidity;
	private Double visibility;

	public Weather() {
	}

	public Weather(int id, String city, Double temperature, LocalDate dateOfRequest, LocalTime timeOfRequest,
			LocalDate dateOfMeasuring, LocalTime timeOfMeasuring, Double windSpeed, String windDirection,
			Double airPressure, String weatherState, Integer humidity, Double visibility) {
		super();
		this.id = id;
		this.city = city;
		this.temperature = temperature;
		this.dateOfRequest = dateOfRequest;
		this.timeOfRequest = timeOfRequest;
		this.dateOfMeasuring = dateOfMeasuring;
		this.timeOfMeasuring = timeOfMeasuring;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
		this.airPressure = airPressure;
		this.weatherState = weatherState;
		this.humidity = humidity;
		this.visibility = visibility;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public Double getAirPressure() {
		return airPressure;
	}

	public void setAirPressure(Double airPressure) {
		this.airPressure = airPressure;
	}

	public LocalDate getDateOfRequest() {
		return dateOfRequest;
	}

	public void setDateOfRequest(LocalDate dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocalDate getDateOfMeasuring() {
		return dateOfMeasuring;
	}

	public void setDateOfMeasuring(LocalDate dateOfMeasuring) {
		this.dateOfMeasuring = dateOfMeasuring;
	}

	public LocalTime getTimeOfMeasuring() {
		return timeOfMeasuring;
	}

	public void setTimeOfMeasuring(LocalTime timeOfMeasuring) {
		this.timeOfMeasuring = timeOfMeasuring;
	}

	public LocalTime getTimeOfRequest() {
		return timeOfRequest;
	}

	public void setTimeOfRequest(LocalTime timeOfRequest) {
		this.timeOfRequest = timeOfRequest;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	public Double getVisibility() {
		return visibility;
	}

	public void setVisibility(Double visibility) {
		this.visibility = visibility;
	}

	public String getWeatherState() {
		return weatherState;
	}

	public void setWeatherState(String weatherState) {
		this.weatherState = weatherState;
	}

	@Override
	public String toString() {
		return "Data i godzina zapisu danych: " + dateOfRequest + " " + timeOfRequest
				+ "\nData i godzina wykonania pomiaru: " + dateOfMeasuring + " " + timeOfMeasuring + "\nMiasto: " + city
				+ "\nStatus pogody: " + weatherState + "\nTemperatura: " + temperature + "\nPrędkość wiatru: "
				+ windSpeed + "\nKierunek wiatru: " + windDirection + "\nCiśnienie: " + airPressure + "\nWilgotność: "
				+ humidity + "\nWidoczność:" + visibility;
	}
}
