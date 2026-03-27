package com.klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.model.TaskAssignment;
import com.klu.service.TaskAssignmentService;

import java.util.List;

@RestController
@RequestMapping("/taskAssign")
@CrossOrigin(origins = "http://localhost:5173")

public class TaskAssignmentController {

	@Autowired
    private TaskAssignmentService taskService;
    @PostMapping("/post")
    public TaskAssignment assignTask(
            @RequestParam Long complaintId,
            @RequestParam int employeeId,
            @RequestParam Long adminId) {

        return taskService.assignTask(complaintId, employeeId, adminId);
    }
    @PutMapping("/update/{id}")
    public TaskAssignment updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return taskService.updateTaskStatus(id, status);
    }
    @GetMapping("/all")
    public List<TaskAssignment> getAllAssignments() {
        return taskService.getAllAssignments();
    }
    @GetMapping("/employee/{id}")
    public List<TaskAssignment> getAssignmentsByEmployee(@PathVariable int id) {
        return taskService.getAssignmentsByEmployee(id);
    }

    

    @DeleteMapping("/delete/{id}")
    public String deleteAssignment(@PathVariable Long id) {
        taskService.deleteAssignment(id);
        return "Task deleted successfully";
    }
}