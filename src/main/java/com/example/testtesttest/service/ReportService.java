package com.example.testtesttest.service;

import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ReportService {
    public int createReport() throws IOException;

    public ResponseEntity<Resource> upload(int id);

    String saveReportToFile(String reportJson);
    ResponseEntity<Resource> getReportById(Integer id);
}
