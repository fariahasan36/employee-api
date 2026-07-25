package com.faria.employee_api.service;

import com.faria.employee_api.entity.Employee;
import com.faria.employee_api.repository.EmployeeRepository;
import com.faria.employee_api.request.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;


    public EmployeeService(EmployeeRepository repository){

        this.repository = repository;
    }

    public Employee createEmployee(Employee employee){
        return repository.save(employee);
    }

    public List<Employee> getAllEmployee(){
        return repository.findAll();
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
