package com.example.testtesttest.service;

import com.example.testtesttest.DTO.EmployeeDTO;
import com.example.testtesttest.pojo.Report;
import com.example.testtesttest.repository.EmployeeRepository;
import com.example.testtesttest.repository.ReportRepository;

import java.util.List;

public class ReportServiceImpl implements ReportService {
    private final EmployeeRepository employeeRepository;
    private final ReportRepository reportRepository;

    public ReportServiceImpl(EmployeeRepository employeeRepository, ReportRepository reportRepository) {
        this.employeeRepository = employeeRepository;
        this.reportRepository = reportRepository;
    }


    @Override
    public Report getStatisticEmployee() {
        Report report = new Report();
        return report;
    }

    @Override
    public Integer getCountEmployees() {
        Integer count = Math.toIntExact(employeeRepository.count());
        return count;
    }

    @Override
    public List<EmployeeDTO> showSalaryMin() {
        return null;
    }

    @Override
    public List<EmployeeDTO> showSalaryMax() {
        return null;
    }

    @Override
    public List<EmployeeDTO> showSalaryAvg() {
        return null;
    }
}