package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klu.model.Notification;
import com.klu.repository.NotificationRepository;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:5173")
public class NotificationController {

    @Autowired
    private NotificationRepository repo;

    // 🔔 Get notifications by role
    @GetMapping("/{role}")
    public List<Notification> getByRole(@PathVariable String role) {
        return repo.findByRoleOrderByCreatedAtDesc(role);
    }

    // ➕ Create notification
    @PostMapping
    public Notification create(@RequestBody Notification n) {
        return repo.save(n);
    }

    // ✅ Mark as read
    @PutMapping("/{id}/read")
    public Notification markRead(@PathVariable Long id) {
        Notification n = repo.findById(id).orElseThrow();
        n.setRead(true);
        return repo.save(n);
    }
}