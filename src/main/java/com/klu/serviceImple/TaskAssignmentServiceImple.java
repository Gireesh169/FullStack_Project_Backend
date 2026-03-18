package com.klu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.TaskAssignment;
import com.klu.repository.TaskAssignmentRepo;
import com.klu.service.TaskAssignmentService;

@Service
public class TaskAssignmentServiceImple implements TaskAssignmentService {

    @Autowired
    private TaskAssignmentRepo taskRepo;

    @Override
    public TaskAssignment assignTask(TaskAssignment task) {
        return taskRepo.save(task);
    }

    @Override
    public List<TaskAssignment> getAllAssignments() {
        return taskRepo.findAll();
    }

    @Override
    public List<TaskAssignment> getAssignmentsByEmployee(int employeeId) {
        return taskRepo.findByEmployeeId(employeeId);
    }

    @Override
    public TaskAssignment updateTaskStatus(Long id, String status) {
        TaskAssignment task = taskRepo.findById(id).orElse(null);
        if (task != null) {
            task.setStatus(status);
            return taskRepo.save(task);
        }
        return null;
    }

    @Override 
    public void deleteAssignment(Long id) {
        taskRepo.deleteById(id);
    }
}