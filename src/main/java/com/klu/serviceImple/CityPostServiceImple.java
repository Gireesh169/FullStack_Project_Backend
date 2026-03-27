package com.klu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.CityPost;
import com.klu.model.User;
import com.klu.repository.CityPostRepo;
import com.klu.repository.UserRepo;
import com.klu.service.CityPostService;

@Service
public class CityPostServiceImple implements CityPostService {

    @Autowired
    private CityPostRepo cityPostRepo;

    @Autowired
    private UserRepo userRepo; // Add this

    @Override
    public CityPost createPost(CityPost post, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new RuntimeException("No Id found to post"));
        post.setUser(user);  // link user to post
        return cityPostRepo.save(post);
    }

   
    @Override
    public List<CityPost> getPostsByUser(Long userId) {
        return cityPostRepo.findByUserId(userId);
    }

    @Override
    public List<CityPost> getAllPosts() {
        return cityPostRepo.findAll();
    }

    @Override
    public List<CityPost> getLatestPosts() {
        return cityPostRepo.findAllByOrderByPostedAtDesc();
    }

    @Override
    public CityPost getPostById(Long id) {
        return cityPostRepo.findById(id).orElse(null);
    }

    @Override
    public List<CityPost> getPostsByLocation(String location) {
        return cityPostRepo.findByLocation(location);
    }

    @Override
    public List<CityPost> getPostsByCategory(String category) {
        return cityPostRepo.findByCategory(category);
    }

   
    @Override
    public CityPost likePost(Long postId, Long userId) {

        CityPost post = cityPostRepo.findById(postId).orElse(null);
        User user = userRepo.findById(userId).orElse(null);

        if (post == null || user == null) return null;

      //Toggle Like
        if (post.getLikedUsers().contains(user)) {
            post.getLikedUsers().remove(user); // UNLIKE
        } else {
            post.getLikedUsers().add(user); // LIKE
        }

        post.setLikesCount(post.getLikedUsers().size());

        return cityPostRepo.save(post);
    }

    @Override
    public void deletePost(Long id) {
        cityPostRepo.deleteById(id);
    }


	


	
}