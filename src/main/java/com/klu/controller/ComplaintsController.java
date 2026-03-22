package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klu.model.Complaints;
import com.klu.service.ComplaintsService;

@RestController
@RequestMapping("/complaints")
public class ComplaintsController {

    @Autowired
    private ComplaintsService complaintsService;

    @PostMapping("/posting")
    public Complaints createComplaint(@RequestBody Complaints complaint) {
        return complaintsService.createComplaint(complaint);
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
}