package com.example.testtesttest.DTO;


import com.example.testtesttest.pojo.Employee;
import com.example.testtesttest.pojo.Position;
import lombok.Getter;

public class EmployeeDTO {

    @Getter
    private Integer id;
    @Getter
    private String name;
    private Integer salary;

    private PositionDto position;
    private int count = 0;

    public EmployeeDTO() {

    }

    public EmployeeDTO(String name, Integer salary, PositionDto position) {
        this.id = count++;
        this.name = name;
        this.salary = salary;
        this.position = position;
    }

    public EmployeeDTO(Integer id, String name, Integer salary, PositionDto position) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.position = position;
    }


    public static EmployeeDTO fromEmployee(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());
        var position = employee.getPosition();
        var positionDto = new PositionDto(position.getId(), position.getName());
        employeeDTO.setPosition(positionDto);
        return employeeDTO;
    }


    public static Employee toEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        var positionDto = employeeDTO.getPosition();
        var position = new Position();
        position.setId(positionDto.getId());
        position.setName(positionDto.getName());
        employee.setPosition(position);
        return employee;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    public PositionDto getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", position=" + position +
                '}';
    }

    public void setPosition(PositionDto position) {
        this.position = position;
    }
}