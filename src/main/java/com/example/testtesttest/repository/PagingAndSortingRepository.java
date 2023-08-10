package com.example.testtesttest.repository;

import com.example.testtesttest.pojo.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface PagingAndSortingRepository extends org.springframework.data.repository.PagingAndSortingRepository<Employee, Integer> {

}
