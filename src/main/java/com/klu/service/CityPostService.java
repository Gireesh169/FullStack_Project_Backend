package com.klu.service;

import com.klu.model.CityPost;
import java.util.List;

public interface CityPostService {


    List<CityPost> getPostsByUser(Long userId);

    List<CityPost> getAllPosts();

    List<CityPost> getLatestPosts();

    CityPost getPostById(Long id);

    List<CityPost> getPostsByLocation(String location);

    List<CityPost> getPostsByCategory(String category);



    void deletePost(Long id);

	CityPost createPost(CityPost post, Integer userId);

	CityPost likePost(Long postId, Long userId);
	

	
}