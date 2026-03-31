package com.klu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.klu.model.Complaints;
@Service
public interface ComplaintsService {
    Complaints createComplaint(Complaints complaint);
    List<Complaints> getAllComplaints();
    Complaints getComplaintById(Long id);
    List<Complaints> getComplaintsByPlace(String place);  // ← fix this
    void deleteComplaint(Long id);
	Complaints approveComplaint(Long id);

	
}