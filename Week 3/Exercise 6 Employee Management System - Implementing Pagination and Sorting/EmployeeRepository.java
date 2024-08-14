package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Method to find all employees with pagination and sorting
    Page<Employee> findAll(Pageable pageable);

    // Method to find all employees sorted by a specific field
    List<Employee> findAll(Sort sort);

    // Custom query method to find employees by name with pagination and sorting
    Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
