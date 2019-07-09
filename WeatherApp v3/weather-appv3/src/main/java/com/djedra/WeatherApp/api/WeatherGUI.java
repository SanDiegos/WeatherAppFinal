package com.djedra.WeatherApp.api;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;

import com.djedra.WeatherApp.api.ReatherResponseConstrains.WeatherAPIAdress;
import com.djedra.WeatherApp.repo.entity.Weather;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

@Route
public class WeatherGUI extends VerticalLayout {

	private final Consumer<Weather> logListener = new Consumer<Weather>() {
		@Override
		public void accept(Weather w) {
			getUI().get().access(() -> {
				String log = getLogText(w);
				textAreaLog.setValue(log);
			});
		};
	};

	WeatherAPI weatherAPI;
	ComboBox<Integer> comboBoxYear = new ComboBox<>("Rok:", Arrays.asList(2018, 2019));
	ComboBox<Integer> comboBoxMonth = new ComboBox<>("Miesiąc:", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
	ComboBox<Integer> comboBoxDay = new ComboBox<>("Dzień:", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
			14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31));
	ComboBox<Integer> comboBoxHour = new ComboBox<>("Godzina zapisu:",
			Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23));
	TextArea textAreaLog = new TextArea("Log aplikacji:");
	TextArea textAreaWeatherData = new TextArea("Pogoda według dnia i godziny:");
	Button button = new Button("Pobierz dane pogodowe");

	@Autowired
	public WeatherGUI(WeatherAPI weatherAPI) {
		this.weatherAPI = weatherAPI;
		weatherAPI.setlogListener(logListener);
		init();
	}

	private static final long serialVersionUID = -1229625455546859690L;

	private void init() {
		comboBoxYear.setWidth("120px");
		comboBoxMonth.setWidth("120px");
		comboBoxDay.setWidth("120px");
		comboBoxHour.setWidth("120px");
		textAreaWeatherData.setWidth("600px");
		textAreaWeatherData.setHeight("260px");
		textAreaLog.setWidth("600px");
		textAreaLog.setHeight("240px");
		button.addClickListener(buttonAction());
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.add(comboBoxYear);
		horizontalLayout.add(comboBoxMonth);
		horizontalLayout.add(comboBoxDay);
		horizontalLayout.add(comboBoxHour);
		add(horizontalLayout);
		add(button);
		add(textAreaWeatherData);
		add(textAreaLog);
		UI ui = UI.getCurrent();
		ui.setPollInterval(1000);
	}

	private ComponentEventListener<ClickEvent<Button>> buttonAction() {
		return clickEvent -> {
			textAreaWeatherData.clear();
			if (walidujDaneZPol(comboBoxYear.getValue(), comboBoxMonth.getValue(), comboBoxDay.getValue(),
					comboBoxHour.getValue())) {
				Optional<List<Weather>> byByRequestDate = weatherAPI.getByDate(
						LocalDate.of(comboBoxYear.getValue(), comboBoxMonth.getValue(), comboBoxDay.getValue()),
						LocalTime.of(comboBoxHour.getValue(), 0));
				byByRequestDate.ifPresent(d -> textAreaWeatherData.setValue(d.get(0).toString()));
			}
		};
	}

	private boolean walidujDaneZPol(Integer year, Integer month, Integer day, Integer hour) {
		boolean isValidate = true;
		if (Objects.isNull(year) || Objects.isNull(month) || Objects.isNull(day) || Objects.isNull(hour)) {
			textAreaWeatherData.setValue("Należy wybrać Datę oraz godzinę.");
			System.err.println("Należy wybrać Datę oraz godzinę.");
			isValidate = false;
		}

		return isValidate;
	}

	private String getLogText(Weather w) {
		return Objects.nonNull(textAreaLog.getValue()) && textAreaLog.getValue().length() > 0
				? textAreaLog.getValue() + "\n\n" + LocalTime.now() + "\nPobrano dane pogodowe z adresu: "
						+ WeatherAPIAdress.META_WEATHER.getUrl() + "\n" + "Miasto" + w.getCity()
				: LocalTime.now() + "\nPobrano dane pogodowe z adresu: " + WeatherAPIAdress.META_WEATHER.getUrl() + "\n"
						+ "Miasto" + w.getCity();
	}

}
