package com.example.testtesttest.service;

import com.example.testtesttest.DTO.EmployeeDTO;
import com.example.testtesttest.DTO.EmployeeFullInfo;
import com.example.testtesttest.pojo.Employee;
import io.micrometer.common.lang.Nullable;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    List<Employee> getEmployees();
    public Integer showSalary();

    Integer showAvgSalary();

    public List<EmployeeDTO> showSalaryMin();

    List<EmployeeDTO> showSalaryMax();

    List<EmployeeDTO> getEmployeesWithSalaryHigherThan(Integer salary);

    List<EmployeeDTO> getEmployeesByIdWithRequired(Integer id);

    void deleteEmployeesWithId(int id);


    //    Employee addEmployee(List <Employee> employee);
    List<EmployeeDTO> addEmployee(List<Employee> employee);
    List<EmployeeDTO> findByIdGreaterThan(int number);


    List<EmployeeFullInfo> getEmployeesFull(int id);

    List<EmployeeFullInfo> getEmployeesFullPosition(@Nullable String position);

    List<EmployeeFullInfo> withHighestSalary();

    List<EmployeeFullInfo> withLowSalary();
    List<Employee> getEmployeesWithPaging(int page, int size);

    void uploadFile(@RequestParam("file") MultipartFile file) throws IOException;

    int generateReport();
    Resource findReport(int id);
    File findReportFile(int id);
    String generateReportFile(String content);
    void update(int id, Employee employee);
}

