package com.example.testtesttest.service;

import com.example.testtesttest.DTO.EmployeeDTO;
import com.example.testtesttest.DTO.EmployeeFullInfo;
import com.example.testtesttest.DTO.EmployeeReport;
import com.example.testtesttest.pojo.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public void addEmployee(Employee employee);
    public List<EmployeeFullInfo> getAllEmployees();

    public List<EmployeeDTO> withHighestSalary();
    public List<EmployeeFullInfo> employeesPosition(Optional position);
    public List<EmployeeFullInfo> fullInfo(int id);
    public List<EmployeeDTO> getEmployeeWithPaging(int pageIndex);
    public EmployeeDTO upload(MultipartFile file) throws IOException;
    public List<EmployeeReport> getReport();
    public EmployeeDTO getEmployeeById(Integer id);


//    List<Employee> getAllEmployee();

//    public List<Employee> showEmployee();
//
//    public Employee createEmployee(int id, String name, int salary, Position position);
//
//    public void editEmployee(int id);
//    public List<Employee> getEmployee(int id);
//    public void deliteEmployee(int id);
//    public List<Employee> getEmployeeSalaryHigher(int salary);
//    public int sumSalary();
//    public int minSalary();
//    public int maxSalary();
//    public List<Employee> highSalary();
}
