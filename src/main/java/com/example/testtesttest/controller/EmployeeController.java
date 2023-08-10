package com.example.testtesttest.controller;

import com.example.testtesttest.DTO.EmployeeDTO;
import com.example.testtesttest.DTO.EmployeeFullInfo;
import com.example.testtesttest.service.EmployeeService;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.testtesttest.repository.ReportRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@NoArgsConstructor(force = true)

public class EmployeeController {

    public  final EmployeeService employeeService;




    @GetMapping("/withHighestSalary")
    public List<EmployeeDTO> withHighestSalary() {
        return (List<EmployeeDTO>) employeeService.withHighestSalary();
    }
    @GetMapping
    public List<EmployeeFullInfo> employeesPosition(@RequestParam("position") Optional position) {
        if (position == null) {
            return employeeService.getAllEmployees();
        } else
            return employeeService.employeesPosition(position);
    }
    @GetMapping("{id}/fullInfo")
    public List<EmployeeFullInfo> fullInfo(@PathVariable Integer id) {
        return (List<EmployeeFullInfo>) employeeService.fullInfo(id);
    }
    @GetMapping( "/page")
    public List<EmployeeDTO> getEmployeeWithPaging(@RequestParam("page") int page) {
        return employeeService.getEmployeeWithPaging(page);
    }
    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)


        public  void upload(@RequestParam("file") MultipartFile file) throws IOException {
            employeeService.upload(file); }

    }
