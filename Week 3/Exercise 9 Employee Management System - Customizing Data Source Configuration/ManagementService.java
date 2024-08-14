package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.repository.employee.EmployeeRepository;
import com.example.EmployeeManagementSystem.repository.department.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagementService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // Service methods using the repositories...
}
