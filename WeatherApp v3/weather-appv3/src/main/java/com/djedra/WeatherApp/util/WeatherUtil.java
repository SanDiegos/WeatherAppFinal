package com.djedra.WeatherApp.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Objects;

import org.springframework.util.StringUtils;

import com.djedra.WeatherApp.api.ReatherResponseConstrains.ConsolidatedWeatherResponseKeys;
import com.djedra.WeatherApp.repo.entity.Weather;

public class WeatherUtil {

	public static Weather convertToWeatherData(Map<String, Object> data) {
		Weather w = new Weather();
		if (Objects.isNull(data)) {
			return w;
		}
		w.setWindDirection((String) data.get(ConsolidatedWeatherResponseKeys.WIND_DIRECTION.getKey()));
		w.setWeatherState((String) data.get(ConsolidatedWeatherResponseKeys.WEATHER_STATE.getKey()));
		w.setHumidity((Integer) data.get(ConsolidatedWeatherResponseKeys.HUMIDITY.getKey()));
		String measureDate = (String) data.get(ConsolidatedWeatherResponseKeys.MEASURE_DATE.getKey());

		Double visibility = (Double) data.get(ConsolidatedWeatherResponseKeys.VISIBILITY.getKey());
		w.setVisibility(Objects.nonNull(visibility)
				? BigDecimal.valueOf(visibility).setScale(2, RoundingMode.HALF_UP).doubleValue()
				: null);
		Double temperature = (Double) data.get(ConsolidatedWeatherResponseKeys.TEMPERATURE.getKey());
		w.setTemperature(Objects.nonNull(temperature)
				? BigDecimal.valueOf(temperature).setScale(2, RoundingMode.HALF_UP).doubleValue()
				: null);
		Double windSpeed = (Double) data.get(ConsolidatedWeatherResponseKeys.WIND_SPEED.getKey());
		w.setWindSpeed(Objects.nonNull(windSpeed)
				? BigDecimal.valueOf(windSpeed).setScale(2, RoundingMode.HALF_UP).doubleValue()
				: null);
		Double airPressure = (Double) data.get(ConsolidatedWeatherResponseKeys.AIR_PRESSURE.getKey());
		w.setAirPressure(Objects.nonNull(airPressure)
				? BigDecimal.valueOf(airPressure).setScale(2, RoundingMode.HALF_UP).doubleValue()
				: null);

		LocalDate dateOfMeasure = createLocalDate(measureDate);
		LocalTime timeOfMerusre = createLocalTime(measureDate);
		w.setDateOfMeasuring(dateOfMeasure);
		w.setTimeOfMeasuring(timeOfMerusre);
		w.setDateOfRequest(LocalDate.now());
		w.setTimeOfRequest(LocalTime.now());
		return w;
	}

	/**
	 * Date from external API example: "2019-07-06T10:06:02.096253Z"
	 * 
	 * @param date
	 * @return
	 */
	private static LocalDate createLocalDate(String dateWithTime) {
		if (isNullOrEmpty(dateWithTime)) {
			return null;
		}
		String[] splitDataTime = dateWithTime.split("T");
		String date = splitDataTime[0];
		String[] yearMonthDay = date.split("-");
		return LocalDate.of(Integer.parseInt(yearMonthDay[0]), Integer.parseInt(yearMonthDay[1]),
				Integer.parseInt(yearMonthDay[2]));
	}

	/**
	 * Date from external API example: "2019-07-06T10:06:02.096253Z"
	 * 
	 * @param date
	 * @return
	 */
	private static LocalTime createLocalTime(String dateWithTime) {
		if (isNullOrEmpty(dateWithTime)) {
			return null;
		}
		String[] splitDataTime = dateWithTime.split("T");
		String timeAndRest = splitDataTime[1];
		String[] splitTime = StringUtils.split(timeAndRest, ".");
		String time = splitTime[0];
		String[] hourMinuteSecond = time.split(":");
		return LocalTime.of(Integer.parseInt(hourMinuteSecond[0]), Integer.parseInt(hourMinuteSecond[1]), Integer.parseInt(hourMinuteSecond[2]));
	}

	public static boolean isNullOrEmpty(String str) {
		return Objects.isNull(str) || str.length() == 0;
	}

}
