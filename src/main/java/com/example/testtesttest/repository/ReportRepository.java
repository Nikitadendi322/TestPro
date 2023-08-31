package com.example.testtesttest.repository;

import com.example.testtesttest.pojo.Employee;
import com.example.testtesttest.pojo.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends CrudRepository<Report, Integer> {

    static Optional<Integer> findById(Long id){
        return Optional.ofNullable(new Employee().getId());
    }

    Optional<Report> findAllById(int id);
}
