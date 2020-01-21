package com.wd.employeeApplication.employee.employee;

import ch.qos.logback.core.CoreConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.IOException;
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
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee(){

        List<Employee> employeeList=employeeService.getAllEmployee();
        return new ResponseEntity<>(employeeList,HttpStatus.OK);
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable (value = "employeeId") Integer employeeId){

        Employee employee=employeeService.getEmployee(employeeId);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity deleteEmployee(@PathVariable(value = "employeeId") int employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{employeeId}")
    public ResponseEntity updateEmployee(@PathVariable(value = "employeeId")int employeeId, @RequestBody(required = true) Employee employee){
        employeeService.updateEmployee(employeeId,employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("uploadfile")
    public ResponseEntity uploadFile(@PathParam("file")MultipartFile file)throws IOException {
        employeeService.readFile(file);
        return new ResponseEntity(HttpStatus.OK);
    }
}
