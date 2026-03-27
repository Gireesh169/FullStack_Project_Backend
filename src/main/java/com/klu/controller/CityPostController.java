package com.klu.controller;

import com.klu.model.CityPost;
import com.klu.service.CityPostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "http://localhost:5173")
public class CityPostController {

    @Autowired
    private CityPostService cityPostService;

    @PostMapping("/create/{userId}")
    public CityPost createPost(@RequestBody CityPost post, @PathVariable Integer userId) {
        return cityPostService.createPost(post, userId);
    }
    @GetMapping("/all")
    public List<CityPost> getAllPosts() {
        return cityPostService.getAllPosts();
    }

    @GetMapping("/feed")
    public List<CityPost> getLatestPosts() {
        return cityPostService.getLatestPosts();
    }

    @GetMapping("/{id}")
    public CityPost getPostById(@PathVariable Long id) {
        return cityPostService.getPostById(id);
    }

   
    @GetMapping("/location/{location}")
    public List<CityPost> getByLocation(@PathVariable String location) {
        return cityPostService.getPostsByLocation(location);
    }

    @GetMapping("/category/{category}")
    public List<CityPost> getByCategory(@PathVariable String category) {
        return cityPostService.getPostsByCategory(category);
    }

    @GetMapping("/user/{userId}")
    public List<CityPost> getByUser(@PathVariable Long userId) {
        return cityPostService.getPostsByUser(userId);
    }
    @PutMapping("/{id}/like")
    public CityPost likePost(@PathVariable Long id, @RequestParam Long userId) {
        return cityPostService.likePost(id, userId);
    }
    
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        cityPostService.deletePost(id);
        return "Post deleted successfully";
    }
}