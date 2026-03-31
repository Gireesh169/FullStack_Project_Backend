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

        Complaints complaint = complaintsRepo.findById(complaintId).orElseThrow();
        Employee emp = employeeRepo.findById(employeeId).orElseThrow();
        User admin = userRepo.findById(adminId).orElseThrow();

        TaskAssignment task = new TaskAssignment();
        task.setComplaint(complaint);
        task.setEmployee(emp);
        task.setAdmin(admin);

        // ONLY ASSIGN → NOT RESOLVE
        complaint.setStatus("IN_PROGRESS");

        return taskRepo.save(task);
    }
    @Override
    public TaskAssignment updateTaskStatus1(Long id, String status) {

        TaskAssignment task = taskRepo.findById(id).orElseThrow();

        // Only employee allowed statuses
        if (!status.equals("IN_PROGRESS") && !status.equals("RESOLVED")) {
            throw new RuntimeException("Invalid status");
        }

        task.setStatus(status);

        // update complaint also
        Complaints complaint = task.getComplaint();
        complaint.setStatus(status);

        return taskRepo.save(task);
    }
    public Complaints approveComplaint(Long complaintId) {

        Complaints complaint = complaintsRepo.findById(complaintId).orElseThrow();

        // Admin can only approve AFTER resolved
        if (!complaint.getStatus().equals("RESOLVED")) {
            throw new RuntimeException("Cannot approve before resolved");
        }

        complaint.setAdminApproved(true);

        return complaintsRepo.save(complaint);
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
	public Complaints approveComplaint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAssignment(Long id) {
		// TODO Auto-generated method stub
		
	}

	
}