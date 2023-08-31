package com.example.testtesttest.DTO;

import lombok.Getter;

public class ReportDto {
    @Getter
    private String position;
    @Getter
    private long count;
    private int maxSelary;
    @Getter
    private int minSelary;
    @Getter
    private double averageSalary;

    public ReportDto(String position, long count, int maxSelary, int minSelary, double averageSalary) {
        this.position = position;
        this.count = count;
        this.maxSelary = maxSelary;
        this.minSelary = minSelary;
        this.averageSalary = averageSalary;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getMaxSalary() {
        return maxSelary;
    }

    public void setMaxSalary(int maxSelary) {
        this.maxSelary = maxSelary;
    }

    public void setMinSelary(int minSelary) {
        this.minSelary = minSelary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }
}
