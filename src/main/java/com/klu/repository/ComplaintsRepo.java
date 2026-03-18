package com.klu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klu.model.Complaints;
@Repository
public interface ComplaintsRepo extends JpaRepository<Complaints, Long> {
    List<Complaints> findByPlace(String place);  // ← use 'place' not 'citizenId'
    // remove findByCitizenId entirely
}