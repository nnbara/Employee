package com.wd.employeeApplication.employee.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void registerEmployee(Employee employee)
    {
        employeeRepository.save(employee);
    }
    public List<Employee> getAllEmployee()
    {
        return employeeRepository.findAll();
    }
    public Employee getEmployee(int employeeId){
        Optional<Employee> employee=employeeRepository.findById(employeeId);
        return employee.get();
    }
    public void deleteEmployee(int employeeId){
         employeeRepository.deleteById(employeeId);
    }
    public void updateEmployee(int employeeId,Employee employee){
            employee.setEmployeeId(employeeId);
            employeeRepository.save(employee);
    }
}
