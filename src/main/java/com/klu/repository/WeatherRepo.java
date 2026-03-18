package com.klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klu.model.Weather;
@Repository
public interface WeatherRepo extends JpaRepository<Weather,Long>{

}
