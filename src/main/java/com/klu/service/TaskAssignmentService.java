package com.klu.service;

import com.klu.model.Complaints;
import com.klu.model.TaskAssignment;
import java.util.List;

public interface TaskAssignmentService {

    TaskAssignment assignTask(Long complaintId, int employeeId, Long adminId);

    List<TaskAssignment> getAllAssignments();

    List<TaskAssignment> getAssignmentsByEmployee(int employeeId);

    TaskAssignment updateTaskStatus1(Long id, String status);

    void deleteAssignment(Long id);
    Complaints approveComplaint();

}