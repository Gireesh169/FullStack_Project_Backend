package com.klu.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "city_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String area;

    private String placeImp;
}