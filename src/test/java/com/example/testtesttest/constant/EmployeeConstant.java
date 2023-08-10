package com.example.testtesttest.constant;

import com.example.testtesttest.pojo.Employee;
import com.example.testtesttest.pojo.Position;
import com.example.testtesttest.pojo.Report;

import java.util.ArrayList;
import java.util.List;

public class EmployeeConstant {
    public static final Employee EMPLOYEE = Employee.builder().build();
    public static final Position pos = new Position(1, "Java");
    public static final Report rep = new Report();
    public static final Employee EMPLOYEE1 = new Employee(1, "Kir", 20000, pos, rep);
    public static final Employee EMPLOYEE2 = new Employee(1, "Arch", 20000, pos, rep);
    public static final Employee EMPLOYEE3 = new Employee(1, "Kir", 20000, pos, rep);

    public static final List<Employee> EMPLOYEE_LIST = new ArrayList<>(List.of(EMPLOYEE1,
            EMPLOYEE2,
            EMPLOYEE3));
}
