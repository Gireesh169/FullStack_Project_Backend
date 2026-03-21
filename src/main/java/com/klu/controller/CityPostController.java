package com.klu.controller;

import com.klu.model.CityPost;
import com.klu.service.CityPostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin
public class CityPostController {

    @Autowired
    private CityPostService cityPostService;

    // Create a new post
    @PostMapping("/create")
    public CityPost createPost(@RequestBody CityPost post) {
        return cityPostService.createPost(post);
    }

    // Get all posts (unordered)
    @GetMapping("/all")
    public List<CityPost> getAllPosts() {
        return cityPostService.getAllPosts();
    }

    // Get posts newest first — use this for the feed
    @GetMapping("/feed")
    public List<CityPost> getLatestPosts() {
        return cityPostService.getLatestPosts();
    }

    // Get a single post by id
    @GetMapping("/{id}")
    public CityPost getPostById(@PathVariable Long id) {
        return cityPostService.getPostById(id);
    }

    // Filter by location
    @GetMapping("/location/{location}")
    public List<CityPost> getByLocation(@PathVariable String location) {
        return cityPostService.getPostsByLocation(location);
    }

    // Filter by category e.g. FLOOD, EVENT, ROAD, POWER, GENERAL
    @GetMapping("/category/{category}")
    public List<CityPost> getByCategory(@PathVariable String category) {
        return cityPostService.getPostsByCategory(category);
    }

    // Get all posts by a user
    @GetMapping("/user/{postedBy}")
    public List<CityPost> getByUser(@PathVariable String postedBy) {
        return cityPostService.getPostsByUser(postedBy);
    }

    // Like a post
    @PutMapping("/{id}/like")
    public CityPost likePost(@PathVariable Long id) {
        return cityPostService.likePost(id);
    }

    // Delete a post
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        cityPostService.deletePost(id);
        return "Post deleted successfully";
    }
}