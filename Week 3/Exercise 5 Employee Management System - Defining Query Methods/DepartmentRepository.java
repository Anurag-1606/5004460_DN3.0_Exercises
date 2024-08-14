package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Using the named query defined in the Department entity
    List<Department> findByName(String name);

    // Using the named query defined in the Department entity
    List<Department> findAllWithMoreThanXEmployees(int count);
}
