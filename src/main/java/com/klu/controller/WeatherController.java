package com.klu.controller;

import com.klu.model.Weather;
import com.klu.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService service;

    @PostMapping("/add")
    public Weather add(@RequestBody Weather weather) {
    	weather.setTime(LocalDateTime.now());
        return service.addWeather(weather);
    }

    @GetMapping("/all")
    public List<Weather> getAll() {
        return service.getAllWeather();
    }

    @GetMapping("/{id}")
    public Weather getById(@PathVariable Long id) {
        return service.getWeatherById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteWeather(id);
        return "Weather deleted";
    }
}