package com.faria.employee_api.controller;

import com.faria.employee_api.entity.Employee;
import com.faria.employee_api.request.EmployeeRequest;
import com.faria.employee_api.service.EmployeeService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    EmployeeService service;

    @Test
    void shouldReturnEmployeeByName() throws Exception {

        Employee employee = new Employee();
        employee.setName("Faria");

        when(service.updateEmployeesByName(
                "Faria",
                new EmployeeRequest()
        )).thenReturn(List.of(employee));


        mockMvc.perform(patch("/api/employees/name/Faria")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                      "name": "Faria Updated"
                    }
                    """))
                .andExpect(status().isOk());
    }
}