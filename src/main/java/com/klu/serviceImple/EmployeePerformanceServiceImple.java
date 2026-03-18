package com.klu.serviceImple;

import com.klu.model.EmployeePerformance;
import com.klu.repository.EmployeePerformanceRepo;
import com.klu.service.EmployeePerformanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeePerformanceServiceImple implements EmployeePerformanceService {

    @Autowired
    private EmployeePerformanceRepo repo;

    @Override
    public EmployeePerformance addPerformance(EmployeePerformance performance) {
        return repo.save(performance);
    }

    @Override
    public EmployeePerformance getPerformanceByEmployee(int employeeId) {
        return repo.findByEmployeeId(employeeId);
    }

    @Override
    public List<EmployeePerformance> getAllPerformance() {
        return repo.findAll();
    }

    @Override
    public EmployeePerformance updatePerformance(EmployeePerformance performance) {
        return repo.save(performance);
    }
}