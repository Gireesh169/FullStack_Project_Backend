package com.klu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.model.FoodOrder;
public interface FoodOrderRepo extends JpaRepository<FoodOrder, Long> {

    List<FoodOrder> findByCitizenId(Long citizenId);

    List<FoodOrder> findByDeliveryEmployee_Id(int employeeId);
}