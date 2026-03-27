package com.klu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.Complaints;
import com.klu.model.Employee;
import com.klu.model.TaskAssignment;
import com.klu.model.User;
import com.klu.repository.ComplaintsRepo;
import com.klu.repository.EmployeeRepo;
import com.klu.repository.TaskAssignmentRepo;
import com.klu.repository.UserRepo;
import com.klu.service.TaskAssignmentService;

@Service
public class TaskAssignmentServiceImple implements TaskAssignmentService {

    @Autowired
    private TaskAssignmentRepo taskRepo;

 

    @Autowired
    private ComplaintsRepo complaintsRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public TaskAssignment assignTask(Long complaintId, int employeeId, Long adminId) {

        Complaints complaint = complaintsRepo.findById(complaintId).orElse(null);
        Employee emp = employeeRepo.findById(employeeId).orElse(null);
        User admin = userRepo.findById(adminId).orElse(null);

        TaskAssignment task = new TaskAssignment();
        task.setComplaint(complaint);
        task.setEmployee(emp);
        task.setAdmin(admin);
        task.setStatus("IN_PROGRESS");
        complaint.setStatus("IN_PROGRESS");

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