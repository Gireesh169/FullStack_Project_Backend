package com.klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.model.Restaurant;

public interface RestaurantRepo extends JpaRepository<Restaurant,Long>{

}
