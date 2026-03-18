package com.klu.serviceImple;

import com.klu.model.Weather;
import com.klu.repository.WeatherRepo;
import com.klu.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherServiceImple implements WeatherService {

    @Autowired
    private WeatherRepo repo;

    @Override
    public Weather addWeather(Weather weather) {
        return repo.save(weather);
    }

    @Override
    public List<Weather> getAllWeather() {
        return repo.findAll();
    }

    @Override
    public Weather getWeatherById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void deleteWeather(Long id) {
        repo.deleteById(id);
    }
}