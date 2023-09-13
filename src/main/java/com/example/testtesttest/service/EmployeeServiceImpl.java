package com.example.testtesttest.service;


import com.example.testtesttest.DTO.EmployeeDTO;
import com.example.testtesttest.DTO.EmployeeFullInfo;
import com.example.testtesttest.exeption.EmployeeNotFoundException;
import com.example.testtesttest.exeption.EmployeeNotValidExeption;
import com.example.testtesttest.pojo.Employee;
import com.example.testtesttest.pojo.Report;
import com.example.testtesttest.repository.EmployeeRepository;
import com.example.testtesttest.repository.ReportRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ReportRepository reportRepository;
    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final ObjectMapper objectMapper;
    private final Optional optional;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ReportRepository reportRepository, ObjectMapper objectMapper, Optional optional, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.reportRepository = reportRepository;
        this.objectMapper = objectMapper;
        this.optional = optional;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<Employee> getAllEmployees() {
        logger.info("Getting all employees");
        return employeeRepository.findAllEmployees().stream()
                .map(EmployeeDTO::toEmployee)
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        return employees;
    }

    @Override
    public Integer showSalary() {
        Integer sum = getEmployees().stream().map(Employee::getSalary).reduce(0, Integer::sum);
        logger.info("The amount = " + sum);
        return sum;
    }

    @Override
    public Integer showAvgSalary() {
        Integer count = getEmployees().size();
        Integer avg = showSalary() / count;
        logger.info("Аverage salary = " + avg);
        return avg;
    }

    @Override
    public List<EmployeeDTO> showSalaryMin() {
        Comparator<EmployeeDTO> comparator = Comparator.comparing(EmployeeDTO::getSalary);
        List<EmployeeDTO> minSalary = getEmployees().stream().
                map(EmployeeDTO::fromEmployee)
                .min(comparator)
                .stream()
                .collect(Collectors.toList());
        logger.info("Minimum salary = " + minSalary);
        return minSalary;
    }

    @Override
    public List<EmployeeDTO> showSalaryMax() {
        Comparator<EmployeeDTO> comparator = Comparator.comparing(EmployeeDTO::getSalary);
        List<EmployeeDTO> maxSalary = getEmployees().stream().map(EmployeeDTO::fromEmployee)
                .max(comparator)
                .stream()
                .collect(Collectors.toList());
        logger.info("Maximum salary = " + maxSalary);
        return maxSalary;
    }

    @Override
    public List<EmployeeDTO> getEmployeesWithSalaryHigherThan(Integer salary) {
        List<EmployeeDTO> salaryEmployeeBiggerThenSalary = getEmployees().stream().map(EmployeeDTO::fromEmployee).filter(i -> i.getSalary() >= salary).toList();
        logger.info("The salary is more than indicated at the entrance = " + salaryEmployeeBiggerThenSalary);
        return salaryEmployeeBiggerThenSalary;
    }

    @Override
    public List<EmployeeDTO> getEmployeesByIdWithRequired(Integer id) {
        List<EmployeeDTO> getIdEmployee = getEmployees().stream().map(EmployeeDTO::fromEmployee).filter(i -> i.equals(getEmployees().get(id))).toList();
        logger.info("There is such an employee");
        return getIdEmployee;
    }

    @Override
    public void deleteEmployeesWithId(int id) {
        logger.debug("Delete employee with ID = " + id);
        employeeRepository.deleteById(id);
    }


    public List<EmployeeDTO> addEmployee(List<Employee> employees) {
        logger.debug("Create employee: {}", employees);
        Optional<Employee> incorrectEmployee = employees.stream()
                .filter(employee -> employee.getSalary() <= 0 || employee.getName() == null ||
                        employee.getName().isEmpty())
                .findFirst();
        if (incorrectEmployee.isPresent()) {
            throw new EmployeeNotValidExeption(incorrectEmployee.get());
        }
        List<Employee> employees1 = (List<Employee>) employeeRepository.saveAll(new ArrayList<>(employees));

        return employees1.stream().map(employeeMapper::toDTO).collect(Collectors.toList());

    }


    @Override
    public void update(int id, Employee employee) {
        logger.debug("Edit employee with ID: {} ", id);
        Employee oldEmployee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(employee.getId()));
        oldEmployee.setSalary(employee.getSalary());
        oldEmployee.setName(employee.getName());
        employeeRepository.update(id, oldEmployee);
    }


    @Override
    public List<EmployeeDTO> findByIdGreaterThan(int number) {
        logger.info("Find employee by id greater than: {} ", number);
        return employeeRepository.findByIdGreaterThan(10000);
    }

    @Override
    public List<EmployeeFullInfo> getEmployeesFull(int id) {
        logger.debug("Get all info employee by id = " + id);
        return employeeRepository.findAllEmployeeFullInfo(id);
    }

    @Override
    public List<EmployeeFullInfo> getEmployeesFullPosition(String position) {
        logger.debug("Get employe by position: {}", position);
        return employeeRepository.getEmployeesFullPosition(position);
    }

    @Override
    public List<EmployeeFullInfo> withHighestSalary() {
        logger.info("Select employee with highest salsry.");
        return employeeRepository.withHighestSalary();
    }

    @Override
    public List<EmployeeFullInfo> withLowSalary() {
        logger.info("Select employee with low salsry.");
        return employeeRepository.withLowSalary();
    }

    @Override
    public List<Employee> getEmployeesWithPaging(int page, int size, ParseTreePattern paginEmployeeRepository) {
        Pageable employeeOfConcretePage = PageRequest.of(page, size);
        Page<Employee> pages = paginEmployeeRepository.findAll(employeeOfConcretePage);
        logger.info("Create paging, wherer page = {}, size = {}", page, size);
        return pages.stream().toList();
    }

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        List<EmployeeDTO> employeeDTO = objectMapper.readValue(file.getBytes(), new TypeReference<List<EmployeeDTO>>() {
        });
        List<Employee> newEmployee = employeeDTO.stream()
                .map(EmployeeDTO::toEmployee)
                .collect(Collectors.toList());
        employeeRepository.saveAll(newEmployee);
        logger.debug("Transferred the file, and saved the new employees: {}", newEmployee);
    }

    @Override
    public int generateReport() {
        logger.debug("Create report");
        var report = employeeRepository.buildReport();
        try {
            var content = objectMapper.writeValueAsString(report);
            var path = generateReportFile(content);
            var reportEntity = new Report();
            reportEntity.setReport(content);
            reportEntity.setPath(path);
            return reportRepository.save(reportEntity).getId();
        } catch (JsonProcessingException e) {
            logger.error("Cannot generate report");
            throw new IllegalStateException("Cannot generate report", e);
        }
    }

    @Transactional
    @Override
    public Resource findReport(int id) {
        logger.info("Find report by id: {} ", id);
        return (Resource) new ByteArrayResource(reportRepository.findAllById(id)
                .orElseThrow(() -> new IllegalStateException("Report with id " + id + " not found"))
                .getReport()
                .getBytes(StandardCharsets.UTF_8));
    }

    @Transactional
    @Override
    public File findReportFile(int id) {
        logger.info("Find report file by id: {}", id);
        return reportRepository.findById(id)
                .map(Report::getPath)
                .map(File::new)
                .orElse(null);
    }

    @Override
    public String generateReportFile(String content) {
        logger.debug("Create report in file");
        var f = new File("report_" + System.currentTimeMillis() + ".json");
        try (var writer = new FileWriter(f)) {
            writer.write(content);
        } catch (IOException e) {
            logger.error("Cannot generate report file");
            throw new UncheckedIOException("Cannot generate report file", e);
        }
        return f.getName();
    }

    public Optional getOptional() {
        return optional;
    }
}




