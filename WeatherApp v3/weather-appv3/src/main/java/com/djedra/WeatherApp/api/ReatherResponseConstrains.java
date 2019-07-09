package com.djedra.WeatherApp.api;

public class ReatherResponseConstrains {

	public enum WeatherResponseKeys {
		CITY("title"), WEATHER_DATA("consolidated_weather");
		private String key;

		WeatherResponseKeys(String key) {
			this.key = key;
		}

		public String getKey() {
			return this.key;
		}
	}

	public enum ConsolidatedWeatherResponseKeys {
		WIND_DIRECTION("wind_direction_compass"), MEASURE_DATE("created"), TEMPERATURE("the_temp"),
		WIND_SPEED("wind_speed"), AIR_PRESSURE("air_pressure"), WEATHER_STATE("weather_state_name"),
		HUMIDITY("humidity"), VISIBILITY("visibility");
		private String key;

		ConsolidatedWeatherResponseKeys(String key) {
			this.key = key;
		}

		public String getKey() {
			return this.key;
		}
	}

	public enum WeatherAPIAdress {

		META_WEATHER("https://www.metaweather.com/api/location/523920/");

		private String url;

		WeatherAPIAdress(String url) {
			this.url = url;
		}

		public String getUrl() {
			return this.url;
		}
	}
}
