package com.example.testtesttest.service;

import com.example.testtesttest.DTO.EmployeeDTO;
import com.example.testtesttest.DTO.EmployeeFullInfo;
import com.example.testtesttest.DTO.EmployeeReport;
import com.example.testtesttest.DTO.FullInfo;
import com.example.testtesttest.pojo.Employee;
import com.example.testtesttest.pojo.Position;
import com.example.testtesttest.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.el.stream.Optional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@EqualsAndHashCode
@ToString
@Data
@Service
@Profile("test")
@NoArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    public EmployeeRepository employeeRepository;
    ObjectMapper objectMapper = new ObjectMapper();
    Logger logger = (Logger) LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Value("${app.env}")
    private String test;


    @Override
    public void addEmployee(Employee employee) {
    }
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        logger.info("Was invoked method for getAllEmployees");
        return employeeRepository.findAllEmployees().stream()
                .map((EmployeeFullInfo employee) -> EmployeeDTO.fromEmployee(employee))
                .collect(Collectors.toList());
    }
    @Override
    public List<EmployeeDTO> withHighestSalary() {
        logger.info("Was invoked method for withHighestSalary");
        return employeeRepository.withHighestSalary().stream()
                .map((Object employee) -> EmployeeDTO.fromEmployee((Employee) employee))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> employeesPosition(java.util.Optional position) {
        return null;
    }

    @Override
    public List<EmployeeDTO> employeesPosition(Optional position) {
        logger.info("Was invoked method for create employee:{} ");
        return employeeRepository.getEmployeesByName(position).stream()
                .map((Object employee) -> EmployeeDTO.fromEmployee((Employee) employee))
                .collect(Collectors.toList());
    }
    @Override
    public List<FullInfo> fullInfo(int id) {
        logger.info("Was invoked method for create employee:{} ");
        return employeeRepository.getEmployeesFullInfo(id).stream()
                .map((Object t) -> EmployeeFullInfo.fromEmployeeFullInfo(t))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeeWithPaging(int pageIndex) {
        try {
            if (pageIndex != 0) {
                logger.info("Was invoked method for create employee:{} ");
                PageRequest employeePage = PageRequest.of(pageIndex, 10);
                Page<Employee> employeeDTOS = employeeRepository.findAll(employeePage);
                return employeeDTOS.stream()
                        .map(EmployeeDTO::fromEmployee).
                        collect(Collectors.toList());
            }
        } catch (Exception e) {
            logger.error("the value was entered incorrectly:{}", pageIndex,e);
            throw new RuntimeException(e);
        }return null;
    }
    @Override
    public EmployeeDTO upload(MultipartFile multipartFile) throws IOException {
        logger.info("Was invoked method for create employee:{} ");
        File file = new File("new.json");
        Files.write(file.toPath(), multipartFile.getBytes());
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee = objectMapper.readValue(file, Employee.class);
        employeeRepository.save(employee);
        return null;
    }

    @Override
    public List<EmployeeReport> getReport() {
        return null;
    }

    @Override
    public EmployeeDTO getEmployeeById(Integer id) {
        return null;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return null;
    }

    @Override
    public List<Employee> showEmployee() {
        return null;
    }

    @Override
    public Employee createEmployee(int id, String name, int salary, Position position) {
        return null;
    }

    @Override
    public void editEmployee(int id) {

    }

    @Override
    public List<Employee> getEmployee(int id) {
        return null;
    }

    @Override
    public void deleteEmployee(int id) {

    }

    @Override
    public List<Employee> getEmployeeSalaryHigher(int salary) {
        return null;
    }

    @Override
    public int sumSalary() {
        return 0;
    }

    @Override
    public int minSalary() {
        return 0;
    }

    @Override
    public int maxSalary() {
        return 0;
    }

    @Override
    public List<Employee> highSalary() {
        return null;
    }
}




