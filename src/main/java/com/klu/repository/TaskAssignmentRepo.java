package com.klu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klu.model.TaskAssignment;
@Repository
public interface TaskAssignmentRepo extends JpaRepository<TaskAssignment,Long>{



	List<TaskAssignment> findByEmployeeId(int employeeId);

}
