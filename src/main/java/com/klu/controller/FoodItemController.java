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

import com.klu.model.FoodItem;
import com.klu.model.Restaurant;
import com.klu.repository.FoodItemRepo;
import com.klu.repository.RestaurantRepo;



@RestController
@RequestMapping("/food")
@CrossOrigin(origins = "*")
public class FoodItemController {

    @Autowired
    private FoodItemRepo foodRepo;

    @Autowired
    private RestaurantRepo restaurantRepo;

    @PostMapping("/create")
    public FoodItem createFood(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("restaurantId") Long restaurantId,
            @RequestParam("image") MultipartFile file
    ) {
        try {
            String uploadDir = "uploads/food/";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path filePath = Paths.get(uploadDir + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());

            Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow();

            FoodItem f = new FoodItem();
            f.setName(name);
            f.setPrice(price);
            f.setRestaurant(restaurant);
            f.setImagePath("http://localhost:8086/uploads/food/" + fileName);

            return foodRepo.save(f);

        } catch (Exception e) {
            throw new RuntimeException("Upload failed");
        }
    }
   
    @GetMapping
    public List getFoodByRestaurant(@RequestParam Long restaurantId) { return foodRepo.findByRestaurantId(restaurantId); }
}