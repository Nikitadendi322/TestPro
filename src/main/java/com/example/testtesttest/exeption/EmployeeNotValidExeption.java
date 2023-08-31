package com.example.testtesttest.exeption;

import com.example.testtesttest.pojo.Employee;

public class EmployeeNotValidExeption extends RuntimeException{
    private final Employee employee;
    public EmployeeNotValidExeption(Employee employee){
        this.employee = employee;
    }
    public Employee getEmployee(){
        return employee;
    }
}
