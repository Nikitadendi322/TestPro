package com.example.testtesttest.repository;

import com.example.testtesttest.DTO.EmployeeDTO;
import com.example.testtesttest.DTO.EmployeeFullInfo;
import com.example.testtesttest.DTO.ReportDto;
import com.example.testtesttest.pojo.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    List<Employee> employees = new ArrayList<>();

    @Query(value = "SELECT * FROM employee",
            nativeQuery = true)
    List<EmployeeDTO> findAllEmployees();


    default Employee add(Employee employee){
        employees.add(employee);
        return employee;
    }
    List<EmployeeDTO> findByIdGreaterThan(int number);
    Page<Employee> findAll(Pageable employeeOfConcretePage);

    @Query("SELECT new com.example.testtesttest.dto" +
            "EmployeeFullInfo(e.id, e.name , e.salary , p.name) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p and e.id= :id")
    List<EmployeeFullInfo> findAllEmployeeFullInfo(@Param("id") int id);
    @Query("SELECT new com.example.testtesttest.dto." +
            "EmployeeFullInfo(e.name , e.salary , p.name) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p and p.name = :position")
    List<EmployeeFullInfo> getEmployeesFullPosition(@Param("position") String position);
    @Query("SELECT new com.example.testtesttest.dto." +
            "EmployeeFullInfo(e.name , e.salary , p.name) " +
            "FROM Employee e  left join Position p on p.id= e.position.id " +
            "WHERE e.salary = (select  max (e.salary) from  Employee e) ")
    List<EmployeeFullInfo> withHighestSalary();
    @Query(value = "SELECT new com.example.testtesttest.dto." +
            "EmployeeFullInfo(e.name , e.salary , p.name) " +
            "FROM Employee e  left join Position p on p.id= e.position.id " +
            "WHERE e.salary = (select  min (e.salary) from  Employee e) ")
    List<EmployeeFullInfo> withLowSalary();
    List<ReportDto> buildReport();
    @Query(value = "update employee set name = :name, " +
            "salary = :salary where id = :id", nativeQuery = true)
    void updateEmployee(int id, String name, int salary);

    public default void update(int id, Employee oldEmployee) {
        int index = findIndexById(id);
        if (index != -1) {
            employees.set(index, oldEmployee);
        }
    }

    private int findIndexById(int id) {
        int index = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }
}
