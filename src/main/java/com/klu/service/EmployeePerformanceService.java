package com.klu.service;

import com.klu.model.EmployeePerformance;
import java.util.List;

public interface EmployeePerformanceService {

    EmployeePerformance addPerformance(EmployeePerformance performance);

    EmployeePerformance getPerformanceByEmployee(int employeeId);

    List<EmployeePerformance> getAllPerformance();

    EmployeePerformance updatePerformance(EmployeePerformance performance);
}