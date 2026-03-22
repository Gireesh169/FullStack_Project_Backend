package com.klu.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "city_posts")
public class CityPost {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String title;

   @Column(length = 1000)
   private String description;

   private String imageUrl;

   private String location;

   // e.g. FLOOD, EVENT, ROAD, POWER, GENERAL
   private String category;

   private int likesCount;

   private String postedBy;
   

   private LocalDateTime postedAt;

   @PrePersist
   public void prePersist() {
       this.postedAt = LocalDateTime.now();
       this.likesCount = 0;
   }

}