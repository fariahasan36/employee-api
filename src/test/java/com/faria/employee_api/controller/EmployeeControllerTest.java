package com.faria.employee_api.controller;

import com.faria.employee_api.entity.Employee;
import com.faria.employee_api.request.EmployeeRequest;
import com.faria.employee_api.security.JwtFilter;
import com.faria.employee_api.service.EmployeeService;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {


    @Autowired
    MockMvc mockMvc;


    @MockitoBean
    EmployeeService service;


    @MockitoBean
    JwtFilter jwtFilter;


    @Test
    void shouldUpdateEmployeeName() throws Exception {


        Employee employee = new Employee();
        employee.setName("Faria Updated");


        when(service.updateEmployeesByName(
                org.mockito.ArgumentMatchers.eq("Faria"),
                org.mockito.ArgumentMatchers.any(EmployeeRequest.class)
        )).thenReturn(List.of(employee));

        mockMvc.perform(
                        patch("/api/employees/name/Faria")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                    {
                      "name": "Faria Updated"
                    }
                """))
                .andExpect(status().isOk());
    }
}