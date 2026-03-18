package com.klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klu.model.Employee;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer>{

}
