package com.klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klu.model.EmployeePerformance;
@Repository
public interface EmployeePerformanceRepo extends JpaRepository<EmployeePerformance,Long>{

	EmployeePerformance findByEmployeeId(int employeeId);

}
