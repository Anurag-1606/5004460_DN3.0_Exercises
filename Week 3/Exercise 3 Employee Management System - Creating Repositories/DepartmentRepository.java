package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Derived query method to find department by name
    Department findByName(String name);

    // Derived query method to check existence by name
    boolean existsByName(String name);
}
