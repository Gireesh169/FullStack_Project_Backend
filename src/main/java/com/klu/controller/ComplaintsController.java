package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.klu.model.Complaints;
import com.klu.service.ComplaintsService;

@RestController
@RequestMapping("/complaints")
@CrossOrigin(origins = "http://localhost:5173")
public class ComplaintsController {

    @Autowired
    private ComplaintsService complaintsService;

    @PostMapping("/posting")
    public Complaints createComplaint(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("place") String place,
            @RequestParam("status") String status,
            @RequestParam("image") MultipartFile file
    ) {
        try {
            String uploadDir = "uploads/";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            java.nio.file.Path filePath = java.nio.file.Paths.get(uploadDir + fileName);

            java.nio.file.Files.createDirectories(filePath.getParent());
            java.nio.file.Files.write(filePath, file.getBytes());

            Complaints c = new Complaints();
            c.setTitle(title);
            c.setDescription(description);
            c.setPlace(place);
            c.setStatus(status);

            c.setImageUrl("http://localhost:8080/uploads/" + fileName);

            return complaintsService.createComplaint(c);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("File upload failed");
        }
    }
    
    @GetMapping("/getComplaints")
    public List<Complaints> getAllComplaints(){
    	return complaintsService.getAllComplaints();
    }
    
    @GetMapping("/getById/{id}")
    public Complaints getComplaintById(@PathVariable long id) {
    	return complaintsService.getComplaintById(id);
    }
    @GetMapping("/getByPlace")
    public List<Complaints> getComplaintsByPlace(@PathVariable String place){
    	return complaintsService.getComplaintsByPlace(place);
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteComplaint(@PathVariable Long id) {
    	complaintsService.deleteComplaint(id);
    }
    @PutMapping("/updateStatus/{id}")
    public Complaints updateStatus(@PathVariable Long id, @RequestBody String status) {
        Complaints c = complaintsService.getComplaintById(id);
        c.setStatus(status);
        return complaintsService.createComplaint(c);
    }
    @PutMapping("/approve/{id}")
    public Complaints approveComplaint(@PathVariable Long id) {
        return complaintsService.approveComplaint(id);
    }
}