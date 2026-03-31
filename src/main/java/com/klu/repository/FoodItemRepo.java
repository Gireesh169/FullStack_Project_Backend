package com.klu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.model.FoodItem;

public interface FoodItemRepo extends JpaRepository<FoodItem,Long>{

	List findByRestaurantId(Long restaurantId);

}
