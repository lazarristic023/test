package com.example.Project.Repository;

import com.example.Project.Model.Company;
import com.example.Project.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo  extends JpaRepository<Employee,Long> {
}
