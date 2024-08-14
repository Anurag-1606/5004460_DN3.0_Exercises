package com.example.EmployeeManagementSystem.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeCustomProjection {

    String getName();

    String getEmail();

    @Value("#{target.department.name}")
    String getDepartmentName();

    // Custom field that concatenates name and department name
    @Value("#{target.name + ' - ' + target.department.name}")
    String getNameWithDepartment();
}
