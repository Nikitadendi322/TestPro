package com.example.testtesttest.service;

import com.example.testtesttest.DTO.EmployeeDTO;
import com.example.testtesttest.pojo.Report;

import java.util.List;

public interface ReportService {
    Report getStatisticEmployee();

    Integer getCountEmployees();

    List<EmployeeDTO> showSalaryMin();

    List<EmployeeDTO> showSalaryMax();

    List<EmployeeDTO> showSalaryAvg();
}
