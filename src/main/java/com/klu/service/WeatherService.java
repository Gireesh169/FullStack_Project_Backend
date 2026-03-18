package com.klu.service;

import com.klu.model.Weather;
import java.util.List;

public interface WeatherService {

    Weather addWeather(Weather weather);

    List<Weather> getAllWeather();

    Weather getWeatherById(Long id);

    void deleteWeather(Long id);
}