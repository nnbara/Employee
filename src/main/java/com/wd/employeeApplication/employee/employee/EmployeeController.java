package com.wd.employeeApplication.employee.employee;

import ch.qos.logback.core.CoreConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("status")
    public String status(){
        return "{\"status\":\"Web service is running...\"}";
    }

    @PostMapping
    public ResponseEntity registerEmployee(@RequestBody(required = true) Employee employee){
        employeeService.registerEmployee(employee);
        /*System.out.println("Employee first name : "+ employee.getFirstName() );*/
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee(){

        List<Employee> employeeList=employeeService.getAllEmployee();

        /*List<Employee> employeeList=new ArrayList<>();
        Employee employee=new Employee();

        employee.setEmployeeId(909);
        employee.setFirstName("Raj");
        employee.setLastName("Dev");
        employee.setDesignation("Project Lead");
        employee.setEmailId("dev@gmail.com");
        employee.setPhoneNumber("9875261789");

        employeeList.add(employee);
        employeeList.add(employee);*/

        return new ResponseEntity<>(employeeList,HttpStatus.OK);
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable (value = "employeeId") Integer employeeId){

        Employee employee=employeeService.getEmployee(employeeId);

       /* employee.setEmployeeId(909);
        employee.setFirstName("Raj");
        employee.setLastName("Dev");
        employee.setDesignation("Project Lead");
        employee.setEmailId("dev@gmail.com");
        employee.setPhoneNumber("9875261789");*/

        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity deleteEmployee(@PathVariable(value = "employeeId") int employeeId){
        employeeService.deleteEmployee(employeeId);
        /*System.out.println("Deleted Employee id :"+employeeId);*/
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{employeeId}")
    public ResponseEntity updateEmployee(@PathVariable(value = "employeeId")int employeeId, @RequestBody(required = true) Employee employee){
        employeeService.updateEmployee(employeeId,employee);
        /*System.out.println("Employee is updated...");*/
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
