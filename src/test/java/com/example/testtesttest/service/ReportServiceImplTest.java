package com.example.testtesttest.service;

import com.example.testtesttest.DTO.ReportDTO;
import com.example.testtesttest.pojo.Position;
import com.example.testtesttest.pojo.Report;
import com.example.testtesttest.repository.ReportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.ExpectedCount.times;

@ExtendWith(MockitoExtension.class)
public class ReportServiceImplTest {
    @Mock
    private ReportRepository reportRepositoryMock;
    @Mock
    private Report reportMock;
    @InjectMocks

    private final Position pos = new Position(1, "Java");
    private final Position pos1 = new Position(1, "Java");
    private final Report rep = new Report();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void CreateReport_OK() throws IOException {

        List<ReportDTO> reportDTOS = new ArrayList<>();
        ReportDTO reportDTO = ReportDTO.builder()
                .id(1)
                .name(pos)
                .build();
        reportDTOS.add(reportDTO);
        when(reportRepositoryMock.createReport()).thenReturn(reportDTOS);
        assertEquals(1,reportRepositoryMock.count());
        verify(reportRepositoryMock, times(1)).createReport();
    }



    @Test
    @ValueSource(ints = {1, 2, 3})
    void Upload_Ok(int id) {
        ResponseEntity<Report> mockResponse = mock(ResponseEntity.class);
        when(reportRepositoryMock.readReportById(id)).thenReturn(mockResponse);
        when(mockResponse.getBody()).thenReturn(reportMock);
        ResponseEntity<Report> result = reportServiceTest.upload(id);
        assertEquals(mockResponse, result);
        assertEquals(reportMock, result.getBody());
    }



}
