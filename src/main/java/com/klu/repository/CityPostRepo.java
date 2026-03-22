package com.klu.repository;

import com.klu.model.CityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CityPostRepo extends JpaRepository<CityPost, Long> {

    List<CityPost> findByLocation(String location);
    
    List<CityPost> findByCategory(String category);
    
    List<CityPost> findAllByOrderByPostedAtDesc();

    List<CityPost> findByUserId(Long userId);
    

}