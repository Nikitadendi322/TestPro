package com.example.testtesttest.service;


import com.example.testtesttest.DTO.EmployeeDTO;
import com.example.testtesttest.DTO.EmployeeFullInfo;
import com.example.testtesttest.pojo.Employee;
import com.example.testtesttest.pojo.Position;
import com.example.testtesttest.pojo.Report;
import com.example.testtesttest.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    public static final Position position = new Position(1, "Java");
    public static final Position positon1 = new Position(1, "Java");
    public static final Report report = new Report();
    public static final EmployeeFullInfo employeeLila = new EmployeeFullInfo(1, "Lila", 200000, "developer");
    public static final EmployeeFullInfo employeeIlya = new EmployeeFullInfo(2, "Ilya", 224000, "developer");
    public static final EmployeeFullInfo employeeKit = new EmployeeFullInfo(3, "Kit", 10000, "tester");
    @Mock
    private EmployeeRepository mockRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    @Mock
    private PaginEmployeeRepository paginEmployeeRepository;

    @Test
    public void getAllEmployees_Test_OK() {

        List<EmployeeDTO> employeeDTOS = employeesTest().stream().map(EmployeeDTO::fromEmployee).collect(Collectors.toList());
        when(mockRepository.findAllEmployees()).thenReturn(employeeDTOS);

        List<Employee> employees = employeeService.getAllEmployees();

        assertEquals(employeeDTOS.size(), employees.size());

    }


    @Test
    void withHighestSalary_Ok() {
        List<EmployeeFullInfo> employees = new ArrayList<>();
        employees.add(employeeIlya);
        employees.add(employeeKit);
        when(mockRepository.withHighestSalary()).thenReturn(employees);
        List<EmployeeFullInfo> employeeFull = employeeService.withHighestSalary();
        verify(mockRepository).withHighestSalary();
        assertEquals(employees, employeeFull);
    }

    @Test
    void employeesPosition_Ok() {
        List<EmployeeFullInfo> employees = new ArrayList<>();
        employees.add(employeeIlya);
        employees.add(employeeKit);
        when(mockRepository.getEmployeesFullPosition(String.valueOf(Optional.of(position)))).thenReturn(employees);
        List<EmployeeFullInfo> expected = new ArrayList<>();
        expected.add(employeeIlya);

        List<EmployeeFullInfo> result = employeeService.getEmployeesFullPosition(String.valueOf(Optional.of(position)));
        assertEquals(expected.get(0).getName(), result.get(0).getName());
    }

    @Test
    void fullInfo_Ok() {
        int id = 1;
        List<EmployeeFullInfo> employees = new ArrayList<>();
        employees.add(employeeIlya);
        employees.add(employeeKit);
        when(mockRepository.findAllEmployeeFullInfo(id)).thenReturn(employees);
        List<EmployeeFullInfo> fullInfos = employeeService.getEmployeesFull(id);
        assertEquals(employees.size(), fullInfos.size());
    }


    @Test
    void getEmployeeWithPaging_OK1() {
        int pageIndex = 1;
        int pageSize = 10;

        List<EmployeeDTO> employeeDTO = employeesTest().stream().map(EmployeeDTO::fromEmployee).collect(Collectors.toList());


        Page<EmployeeDTO> employeePage = new PageImpl<>(employeeDTO);
        when(paginEmployeeRepository.findAll(any(Pageable.class))).thenReturn((Page) employeePage);

        List<Employee> actualEmployeeDTOList = employeeService.getEmployeesWithPaging(pageIndex, pageSize);

        assertEquals(employeeDTO, actualEmployeeDTOList);
    }


    public EmployeeServiceImplTest(EmployeeRepository mockRepository, EmployeeServiceImpl employeeService, PaginEmployeeRepository paginEmployeeRepository) {
        this.mockRepository = mockRepository;
        this.employeeService = employeeService;
        this.paginEmployeeRepository = paginEmployeeRepository;
    }

    @Test
    void upload_NO_OK_Exception() throws NullPointerException {
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "multipartFile",
                "filename.txt",
                "application/json",
                "{\"id\" \":111\",\"name\": \"Kira\", \"salary\": 210000\",\"position:\"JS\"}".getBytes());

        assertThrows(NullPointerException.class, () -> employeeService.uploadFile(mockMultipartFile));
    }

    public List<Employee> employeesTest() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "Ilya", 100000, new Position(1, "developer")));
        employeeList.add(new Employee(2, "Anna", 90000, new Position(2, "tester")));
        employeeList.add(new Employee(3, "Ilyas", 1100000, new Position(1, "developer")));

        return employeeList;
    }



}
