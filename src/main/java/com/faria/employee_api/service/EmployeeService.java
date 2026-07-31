package com.faria.employee_api.service;

import com.faria.employee_api.entity.Employee;
import com.faria.employee_api.repository.EmployeeRepository;
import com.faria.employee_api.request.EmployeeRequest;
import com.faria.employee_api.response.EmployeeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    private static final Logger log =
            LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeService(EmployeeRepository repository){

        this.repository = repository;
    }

    public Employee createEmployee(Employee employee){
        try {

            return repository.save(employee);
    } catch(Exception e){
            log.error("Failed creating employee", e);
            throw e;
        }}

    public List<EmployeeResponse> getAllEmployee(){
        try {return repository.findAll()
                .stream()
                .map(employee -> new EmployeeResponse(employee.getName())).toList()
                ;} catch(Exception e){

            log.error(
                    "Could not get any employee",
                    e
            );

            throw e;
        }
    }

    public Optional<Employee> getEmployeeById(long id){
        return repository.findById(id);
    }

    public List<Employee> getEmployeeByName(String name){
        return repository.findAllByName(name);
    }

    public List<Employee> updateEmployeesByName(String name, EmployeeRequest employeeRequest){
        List<Employee> employeeList = repository.findAllByName(name);
        for(Employee employee:employeeList){
            employee.setName(employeeRequest.getName());
        }

        return repository.saveAll(employeeList);
    }

    public void deleteEmployeeById(long id){
        repository.deleteById(id);

    }
}
