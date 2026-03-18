package com.klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.model.TaskAssignment;
import com.klu.service.TaskAssignmentService;

import java.util.List;

@RestController
@RequestMapping("/taskAssign")
public class TaskAssignmentController {

    @Autowired
    private TaskAssignmentService taskService;

    @PostMapping("/post")
    public TaskAssignment assignTask(@RequestBody TaskAssignment task) {
        return taskService.assignTask(task);
    }
    @GetMapping("/all")
    public List<TaskAssignment> getAllAssignments() {
        return taskService.getAllAssignments();
    }
    @GetMapping("/employee/{id}")
    public List<TaskAssignment> getAssignmentsByEmployee(@PathVariable int id) {
        return taskService.getAssignmentsByEmployee(id);
    }

    @PutMapping("/update/{id}")
    public TaskAssignment updateStatus(@PathVariable Long id, @RequestParam String status) {
        return taskService.updateTaskStatus(id, status);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAssignment(@PathVariable Long id) {
        taskService.deleteAssignment(id);
        return "Task deleted successfully";
    }
}