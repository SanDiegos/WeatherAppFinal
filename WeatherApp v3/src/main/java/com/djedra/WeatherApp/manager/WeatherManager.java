package com.djedra.WeatherApp.manager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djedra.WeatherApp.repo.WeatherRepo;
import com.djedra.WeatherApp.repo.entity.Weather;

@Service
public class WeatherManager {
	private WeatherRepo weatherRepo;

	@Autowired
	public WeatherManager(WeatherRepo weatherRepo) {
		this.weatherRepo = weatherRepo;
	}

	public Optional<List<Weather>> findByRequestDate(LocalDate date, LocalTime time) {
		return weatherRepo.findByRequestDate(date, time);
	}

	public Weather save(Weather weather) {
		return weatherRepo.save(weather);
	}

	public List<Weather> getAll() {
		return weatherRepo.findAll();
	}

}
