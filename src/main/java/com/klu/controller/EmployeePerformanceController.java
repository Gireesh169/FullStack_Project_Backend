package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klu.model.EmployeePerformance;
import com.klu.service.EmployeePerformanceService;




@RestController
@RequestMapping("/employeePerformance")
public class EmployeePerformanceController {

    @Autowired
    private EmployeePerformanceService employeePerformanceService;

    @PostMapping("/post")
    public EmployeePerformance addPerformance(@RequestBody EmployeePerformance performance) {
        return employeePerformanceService.addPerformance(performance);
    }
    @GetMapping("/getById")
    public EmployeePerformance getPerformanceByEmployees(@PathVariable int employeeId) {
    	return employeePerformanceService.getPerformanceByEmployee(employeeId);
    }
    @GetMapping("/getAll")
    public List<EmployeePerformance> getAllPerformance() {
        return employeePerformanceService.getAllPerformance();
    }
    @GetMapping("/update")
    public EmployeePerformance updatePerformance(EmployeePerformance performance) {
        return employeePerformanceService.updatePerformance(performance);
    }
}