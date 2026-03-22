package com.klu.repository;

import com.klu.model.CityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityPostRepo extends JpaRepository<CityPost, Long> {

    // Get all posts by a specific location
    List<CityPost> findByLocation(String location);

    // Get all posts by category (e.g. FLOOD, EVENT)
    List<CityPost> findByCategory(String category);

    // Get all posts by a specific user
    List<CityPost> findByPostedBy(String postedBy);

    // Get latest posts first
    List<CityPost> findAllByOrderByPostedAtDesc();
}