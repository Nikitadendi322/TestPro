package com.example.testtesttest.service;

import com.example.testtesttest.DTO.EmployeeDTO;
import com.example.testtesttest.DTO.EmployeeFullInfo;
import com.example.testtesttest.DTO.FullInfo;
import com.example.testtesttest.controller.EmployeeController;
import com.example.testtesttest.pojo.Employee;
import com.example.testtesttest.pojo.Position;
import com.example.testtesttest.pojo.Report;
import com.example.testtesttest.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    public static final Position pos = new Position(1, "Java");
    public static final Position pos1 = new Position(1, "Java");
    public static final Report rep = new Report();
    private static EmployeeDTO employeeDto;
    private static FullInfo fullInfo;
    private static ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    private EmployeeRepository employeeRepositoryMock;
    private static EmployeeController employeeController;
    @InjectMocks
    private EmployeeServiceImpl employeeServiceTest;

    @Test
    void getAllEmployees_Ok() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Nik", 200000, pos, rep));
        employees.add(new Employee(2, "Semen", 30000, pos, rep));
        when(employeeRepositoryMock.findAllEmployees()).thenReturn((List<EmployeeFullInfo>) employeeServiceTest);
        List<EmployeeDTO> employeeDTOs = employeeServiceTest.getAllEmployees();
        assertEquals(employees.size(), employeeDTOs.size());
    }

    @Test
    void withHighestSalary_Ok() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "pavl", 200000, pos, rep));
        employees.add(new Employee(2, "Semen", 30000, pos, rep));
        when(employeeRepositoryMock.withHighestSalary()).thenReturn(Collections.singleton(employees));
        List<EmployeeDTO> highestPaidEmployees = employees.stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
        List<EmployeeDTO> employeeDTOs = employeeServiceTest.withHighestSalary();
        verify(employeeRepositoryMock).withHighestSalary();
        assertEquals(highestPaidEmployees, employeeDTOs);

    }

    @Test
    void employeesPosition_Ok() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Pavl", 200000, pos, rep));
        employees.add(new Employee(2, "Semen", 30000, pos1, rep));
        when(employeeRepositoryMock.getEmployeesByName(Optional.of(pos))).thenReturn(Collections.singleton(employees));
        List<Employee> expected = new ArrayList<>();
        expected.add(new Employee(1, "Kir", 200000, pos, rep));
        List<EmployeeDTO> employeeDTOList = expected.stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
        List<EmployeeDTO> result = employeeServiceTest.employeesPosition(Optional.of(pos));
        assertEquals(employeeDTOList.get(0).getName(), result.get(0).getName());
    }

    @Test
    void fullInfo_Ok() {
        int id = 1;
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Pavl", 200000, pos, rep));
        employees.add(new Employee(2, "Semen", 30000, pos1, rep));
        when(employeeRepositoryMock.getEmployeesFullInfo(id)).thenReturn(Collections.singleton(employees));
        List<FullInfo> fullInfos = employeeServiceTest.fullInfo(id);
        assertEquals(employees.size(), fullInfos.size());
    }

    @Test
    void getEmployeeWithPaging_OK() {
        int pageIndex = 1;
        int pageSize = 10;
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Nik", 200000, pos, rep));
        employees.add(new Employee(2, "Semen", 30000, pos1, rep));
        Page<Employee> employeePage = new PageImpl<>(employees);
        when(employeeRepositoryMock.findAll(any(Pageable.class))).thenReturn(employeePage);
        List<EmployeeDTO> expectedEmployeeDTOList = employees.stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());

        List<EmployeeDTO> actualEmployeeDTOList = employeeServiceTest.getEmployeeWithPaging(pageIndex);

        assertEquals(expectedEmployeeDTOList, actualEmployeeDTOList);
    }

    @Test
    void upload_NO_OK_Exception() throws IOException {
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "multipartFile",
                "filename.txt",
                "application/json",
                "{\"id\" \":111\",\"name\": \"Pavl\", \"salary\": 20000\",\"position:\"JS\"}".getBytes());

        assertThrows(IOException.class, () -> employeeServiceTest.upload(mockMultipartFile));
    }


}
