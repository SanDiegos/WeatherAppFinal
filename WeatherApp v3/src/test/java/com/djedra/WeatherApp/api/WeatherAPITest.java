package com.djedra.WeatherApp.api;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.djedra.WeatherApp.manager.WeatherManager;
import com.djedra.WeatherApp.repo.entity.Weather;

@RunWith(SpringRunner.class)
@WebMvcTest(value = WeatherAPI.class, secure = false)
public class WeatherAPITest {

	@MockBean
	private WeatherAPI weatherAPI;
	@MockBean
	WeatherManager weatherManager;

	Weather w = new Weather();

	@Test
	public void add() throws Exception {

		Mockito.when(weatherAPI.add()).thenReturn(w);
	}

	@Test
	public void getByDate() {
		Weather w = new Weather(1, "Zielona gora", new Double(22.22), LocalDate.now(), LocalTime.now(), LocalDate.now(),
				LocalTime.now(), new Double(13.13), "N", new Double(1006.6), "Windy", new Integer(70),
				new Double(15.15));
		weatherManager.save(w);
		List<Weather> all = weatherAPI.getAll();
		assertNotNull(all);
	}
}
