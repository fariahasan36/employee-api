package com.faria.employee_api.controller;

import com.faria.employee_api.entity.Employee;
import com.faria.employee_api.request.EmployeeRequest;
import com.faria.employee_api.response.EmployeeResponse;
import com.faria.employee_api.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service){
        this.service = service;
    }

    @PostMapping
    public EmployeeResponse createEmployee(
            @RequestBody EmployeeRequest employeeRequest){
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());

        Employee savedEmployee =  service.createEmployee(employee);

        return new EmployeeResponse(savedEmployee.getName());

    }

    @GetMapping
    public List<Employee> getAllEmployee(){
        return service.getAllEmployee();
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable long id){
        return service.getEmployeeById(id);
    }

    @GetMapping("/search")
    public List<Employee> getEmployeeByName(@RequestParam String name){
        return service.getEmployeeByName(name);
    }

    @PatchMapping("/name/{name}")
    public List<Employee> updateEmployeeByName(@PathVariable String name, @RequestBody EmployeeRequest employeeRequest){

         return service.updateEmployeesByName(name, employeeRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable Long id){
        service.deleteEmployeeById(id);
    }
}
