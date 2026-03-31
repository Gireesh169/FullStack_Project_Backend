package com.klu.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="foodorder")
public class FoodOrder {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
private User citizen;

@ManyToOne
private Restaurant restaurant;

private double totalAmount;

private String status; 


@ManyToOne
private Employee deliveryEmployee;

private LocalDateTime createdAt;

}