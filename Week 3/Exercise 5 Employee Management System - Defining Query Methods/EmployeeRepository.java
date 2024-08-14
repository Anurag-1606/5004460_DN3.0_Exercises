package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find employees by name
    List<Employee> findByNameContainingIgnoreCase(String name);

    // Find employees by department name
    List<Employee> findByDepartmentName(String departmentName);

    // Find employees by email domain
    List<Employee> findByEmailEndingWith(String domain);

    // Find employees whose name starts with a specific string
    List<Employee> findByNameStartingWith(String prefix);
}
