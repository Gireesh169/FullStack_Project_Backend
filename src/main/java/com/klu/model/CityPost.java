package com.klu.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user; // proper foreign key relationship
   

   private LocalDateTime postedAt;

   @PrePersist
   public void prePersist() {
       this.postedAt = LocalDateTime.now();
       this.likesCount = 0;
   }
   @ManyToMany
   @JoinTable(
       name = "post_likes",
       joinColumns = @JoinColumn(name = "post_id"),
       inverseJoinColumns = @JoinColumn(name = "user_id")
   )
   private Set<User> likedUsers = new HashSet<>();

}