package com.klu.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.klu.model.Restaurant;
import com.klu.repository.RestaurantRepo;


@RestController
@RequestMapping("/restaurants")
@CrossOrigin(origins = "*")
public class RestaurantController {

    @Autowired
    private RestaurantRepo restaurantRepo;

    @PostMapping("/create")
    public Restaurant createRestaurant(
            @RequestParam("name") String name,
            @RequestParam("location") String location,
            @RequestParam("image") MultipartFile file
    ) {
        try {
            String uploadDir = "uploads/food/";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path filePath = Paths.get(uploadDir + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());

            Restaurant r = new Restaurant();
            r.setName(name);
            r.setLocation(location);
            r.setImagePath("http://localhost:8086/uploads/food/" + fileName);

            return restaurantRepo.save(r);

        } catch (Exception e) {
            throw new RuntimeException("Upload failed");
        }
    }
    @GetMapping
    public List getAllRestaurants() {
    return restaurantRepo.findAll();
    }
}