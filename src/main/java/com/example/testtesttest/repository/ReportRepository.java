package com.example.testtesttest.repository;


import com.example.testtesttest.pojo.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends CrudRepository<Report, Integer> {

    List<Report> findAll();

    @Override
    Optional<Report> findById(Integer integer);
}
