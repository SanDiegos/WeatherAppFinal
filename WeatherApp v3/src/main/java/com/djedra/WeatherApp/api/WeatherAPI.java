package com.djedra.WeatherApp.api;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import javax.swing.text.html.HTMLEditorKit.LinkController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.djedra.WeatherApp.api.WeatherExterenalAPIResponseConstrains.WeatherAPIAdress;
import com.djedra.WeatherApp.api.WeatherExterenalAPIResponseConstrains.WeatherResponseKeys;
import com.djedra.WeatherApp.manager.WeatherManager;
import com.djedra.WeatherApp.repo.entity.Weather;
import com.djedra.WeatherApp.util.WeatherUtil;

@RestController
@RequestMapping("/api/weather")
public class WeatherAPI {

	Logger logger = LogManager.getLogger(LinkController.class);
	private WeatherManager weatherManager;
	private Consumer<Weather> logListener;

	@Autowired
	public WeatherAPI(WeatherManager weatherManager) {
		this.weatherManager = weatherManager;
	}

	@RequestMapping
	public Optional<List<Weather>> getByDate(@RequestParam LocalDate date, @RequestParam LocalTime time) {
		logger.info("Pobrano dane pogodowe z bazy dla daty: " + date + " " + time);
		return weatherManager.findByRequestDate(date, time);
	}

	@RequestMapping("/all")
	public List<Weather> getAll() {
		logger.info("Pobrano wszystkie dane pogodowe z bazy");
		return weatherManager.getAll();
	}

	@Scheduled(cron = "0 0 0-23 * * *")
	public Weather add() {
		Map body = downloadWeatherData();
		Weather wearther = parseWeatherData(body);
		if (Objects.isNull(wearther)) {
			return null;
		}
		wearther.setTimeOfRequest(LocalTime.of(LocalTime.now().getHour(), 0));
		if (Objects.nonNull(logListener)) {
			logListener.accept(wearther);
		}
		logger.info(WeatherUtil.getDataDownlowadedLogMessage(wearther));
		return Objects.nonNull(wearther) ? weatherManager.save(wearther) : null;
	}

	public Map downloadWeatherData() {
		RestTemplate rest = new RestTemplate();
		ResponseEntity<Map> exchange = rest.exchange(WeatherAPIAdress.META_WEATHER.getUrl(), HttpMethod.GET,
				HttpEntity.EMPTY, Map.class);
		if (Objects.isNull(exchange)) {
			return null;
		}
		Map body = exchange.getBody();
		return body;
	}

	public Weather parseWeatherData(Map body) {
		if (Objects.isNull(body)) {
			return null;
		}
		List<Map> weathers = (List<Map>) body.get(WeatherResponseKeys.WEATHER_DATA.getKey());
		Map<String, Object> pomiar = weathers.get(0);
		Weather weather = WeatherUtil.convertToWeatherData(pomiar);
		String city = (String) body.get(WeatherResponseKeys.CITY.getKey());
		weather.setCity(city);
		return weather;
	}

	public void setlogListener(Consumer<Weather> logListener) {
		this.logListener = logListener;
	}

}
