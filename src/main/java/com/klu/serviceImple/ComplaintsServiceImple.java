package com.klu.serviceImple;

import com.klu.model.Complaints;
import com.klu.repository.ComplaintsRepo;
import com.klu.service.ComplaintsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintsServiceImple implements ComplaintsService {

	@Autowired
	private ComplaintsRepo complaintsservice;

    @Override
    public Complaints createComplaint(Complaints complaint) {
        return complaintsservice.save(complaint);
    }

    @Override
    public List<Complaints> getAllComplaints() {
        return complaintsservice.findAll();
    }

    @Override
    public Complaints getComplaintById(Long id) {
        return complaintsservice.findById(id).orElse(null);
    }

    public List<Complaints> getComplaintsByPlace(String place) {
        return complaintsservice.findByPlace(place);
    }

    @Override
    public void deleteComplaint(Long id) {
    	complaintsservice.deleteById(id);
    }

	@Override
	public Complaints approveComplaint(Long complaintId) {
	    Complaints complaint = complaintsservice.findById(complaintId).orElseThrow();

	    if (!complaint.getStatus().equals("RESOLVED")) {
	        throw new RuntimeException("Cannot approve before resolved");
	    }

	    complaint.setAdminApproved(true);

	    return complaintsservice.save(complaint);
	}
}