package com.djedra.WeatherApp.repo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.djedra.WeatherApp.repo.entity.Weather;

@Repository
public interface WeatherRepo extends JpaRepository<Weather, Integer> {

	@Query("SELECT t FROM Weather t WHERE t.dateOfRequest = :date AND t.timeOfRequest = :time")
	public Optional<List<Weather>> findByRequestDate(@Param("date") LocalDate dateOfRequest,
			@Param("time") LocalTime timeOfRequest);

}
