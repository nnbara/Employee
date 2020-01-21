package com.wd.employeeApplication.employee.employee;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
    public void readFile(MultipartFile multipartFile)throws IOException {
        File file=new File("file");
        FileOutputStream fos=new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();

        FileInputStream excelFile=new FileInputStream(file);
        Workbook workbook=new XSSFWorkbook(excelFile);
        Sheet dataTypeSheet=workbook.getSheetAt(0);

        Iterator<Row> iterator=dataTypeSheet.iterator();

        List<Employee> employeeList=new ArrayList<>();

        while (iterator.hasNext()){

            Employee employee=new Employee();
            DataFormatter dataFormatter=new DataFormatter();
            Row row=iterator.next();

            Iterator<Cell> cellIterator=row.cellIterator();

            if(cellIterator.hasNext()){
                Cell cell=cellIterator.next();
                employee.setFirstName(dataFormatter.formatCellValue(cell));
            }
            if(cellIterator.hasNext()){
                Cell cell=cellIterator.next();
                employee.setLastName(dataFormatter.formatCellValue(cell));
            }
            if(cellIterator.hasNext()){
                Cell cell=cellIterator.next();
                employee.setDesignation(dataFormatter.formatCellValue(cell));
            }
            if(cellIterator.hasNext()){
                Cell cell=cellIterator.next();
                employee.setEmailId(dataFormatter.formatCellValue(cell));
            }
            if(cellIterator.hasNext()){
                Cell cell=cellIterator.next();
                employee.setPhoneNumber(dataFormatter.formatCellValue(cell));
            }

            employeeList.add(employee);
        }
        for(Employee emp: employeeList){
            employeeRepository.save(emp);
        }

    }
}
