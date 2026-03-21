package com.klu.service;

import com.klu.model.CityPost;

import java.util.List;

public interface CityPostService {

    CityPost createPost(CityPost post);

    List<CityPost> getAllPosts();

    List<CityPost> getLatestPosts();

    CityPost getPostById(Long id);

    List<CityPost> getPostsByLocation(String location);

    List<CityPost> getPostsByCategory(String category);

    List<CityPost> getPostsByUser(String postedBy);

    CityPost likePost(Long id);

    void deletePost(Long id);
}