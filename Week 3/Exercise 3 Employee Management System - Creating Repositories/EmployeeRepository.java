package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Derived query method to find employees by department
    List<Employee> findByDepartmentId(Long departmentId);

    // Derived query method to find employees by name
    List<Employee> findByNameContainingIgnoreCase(String name);

    // Derived query method to find an employee by email
    Employee findByEmail(String email);
}
