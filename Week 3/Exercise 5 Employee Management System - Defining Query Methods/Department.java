package com.example.EmployeeManagementSystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "departments")
@NamedQueries({
    @NamedQuery(
        name = "Department.findByName",
        query = "SELECT d FROM Department d WHERE d.name = :name"
    ),
    @NamedQuery(
        name = "Department.findAllWithMoreThanXEmployees",
        query = "SELECT d FROM Department d WHERE SIZE(d.employees) > :count"
    )
})
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employees;
}
