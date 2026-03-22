package com.klu.serviceImple;

import com.klu.model.CityPost;
import com.klu.repository.CityPostRepo;
import com.klu.service.CityPostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityPostServiceImple implements CityPostService {

    @Autowired
    private CityPostRepo cityPostRepo;

    @Override
    public CityPost createPost(CityPost post) {
        return cityPostRepo.save(post);
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
    public List<CityPost> getPostsByUser(String postedBy) {
        return cityPostRepo.findByPostedBy(postedBy);
    }

    @Override
    public CityPost likePost(Long id) {
        CityPost post = cityPostRepo.findById(id).orElse(null);
        if (post != null) {
            post.setLikesCount(post.getLikesCount() + 1);
            return cityPostRepo.save(post);
        }
        return null;
    }

    @Override
    public void deletePost(Long id) {
        cityPostRepo.deleteById(id);
    }
}