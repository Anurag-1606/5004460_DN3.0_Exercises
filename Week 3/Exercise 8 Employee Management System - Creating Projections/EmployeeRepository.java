package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.projection.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Use class-based projection with constructor expression
    @Query("SELECT new com.example.EmployeeManagementSystem.projection.EmployeeDTO(e.name, e.email, d.name) FROM Employee e JOIN e.department d")
    List<EmployeeDTO> findAllEmployeeDTOs();
}
