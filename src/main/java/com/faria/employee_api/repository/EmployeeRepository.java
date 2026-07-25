package com.faria.employee_api.repository;

import com.faria.employee_api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  EmployeeRepository
     extends JpaRepository<Employee, Long>

    {
        List<Employee> findAllByName(String name);

    }
