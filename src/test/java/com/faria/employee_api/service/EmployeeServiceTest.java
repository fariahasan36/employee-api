package com.faria.employee_api.service;

import com.faria.employee_api.entity.Employee;
import com.faria.employee_api.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    EmployeeRepository repository;

    @InjectMocks
    EmployeeService service;

    @Test
    void shouldReturnEmployeesByName() {
        // Arrange
        Employee employee = new Employee();
        employee.setName("Faria");

        when(repository.findAllByName("Faria"))
                .thenReturn(List.of(employee));

        //Act
        List<Employee> employees = service.getEmployeeByName("Faria");

        //Assert
        assertEquals(1, employees.size());
        assertEquals("Faria", employees.get(0).getName());
    }
}