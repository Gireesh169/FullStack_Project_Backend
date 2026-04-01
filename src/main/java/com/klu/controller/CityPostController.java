package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.klu.model.CityPost;
import com.klu.service.CityPostService;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*")
public class CityPostController {

    @Autowired
    private CityPostService cityPostService;

    @PostMapping("/create/{userId}")
    public CityPost createPost(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("location") String location,
            @RequestParam("category") String category,
            @RequestParam("image") MultipartFile file,
            @PathVariable Integer userId
    ) {
        try {
            // Save file
            String uploadDir = "uploads/";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            java.nio.file.Path filePath = java.nio.file.Paths.get(uploadDir + fileName);
            java.nio.file.Files.createDirectories(filePath.getParent());
            java.nio.file.Files.write(filePath, file.getBytes());

            // Create object
            CityPost post = new CityPost();
            post.setTitle(title);
            post.setDescription(description);
            post.setLocation(location);
            post.setCategory(category);
            post.setImageUrl("http://localhost:8080/uploads/" + fileName);

            return cityPostService.createPost(post, userId);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("City Post upload failed");
        }
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