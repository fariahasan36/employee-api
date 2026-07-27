package com.faria.employee_api.repository;

import com.faria.employee_api.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    @Test
    void shouldFindEmployeeByName() {

        Employee employee = new Employee();
        employee.setName("Faria");
        employee.setEmail("faria@test.com");

        repository.save(employee);

        List<Employee> employees = repository.findAllByName("Faria");

        assertEquals(1, employees.size());
        assertEquals("Faria", employees.get(0).getName());
    }
}
