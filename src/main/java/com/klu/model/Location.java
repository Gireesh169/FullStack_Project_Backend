package com.klu.model;

import jakarta.persistence.*;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double lat;
    private Double lng;
    private String type;

    // Constructors
    public Location() {}

    public Location(double lat, double lng, String type) {
        this.lat = lat;
        this.lng = lng;
        this.type = type;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public double getLat() { return lat; }
    public void setLat(double lat) { this.lat = lat; }

    public double getLng() { return lng; }
    public void setLng(double lng) { this.lng = lng; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}