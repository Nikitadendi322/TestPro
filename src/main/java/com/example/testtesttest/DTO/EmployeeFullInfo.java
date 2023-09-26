package com.example.testtesttest.DTO;

import lombok.*;

public class EmployeeFullInfo {

    private int id;
    @Getter
    private String name;

    @Getter
    private Integer salary;

    @Getter
    private String position;

    private int count = 0;


    public EmployeeFullInfo(int id, String name, Integer salary, String positionName) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.position = positionName;
    }
    public EmployeeFullInfo(String name, Integer salary, String positionName) {
            this.id = count++;
            this.name = name;
            this.salary = salary;
            this.position = positionName;
        }

    public void setPosition (String position){
            this.position = position;
        }
        public void setId ( int id){
            this.id = id;
        }

    public void setName (String name){
            this.name = name;
        }

    public void setSalary (Integer salary){
            this.salary = salary;
        }
        public String getPositionName () {
            return position;
        }
        public void setPositionName (String positionName){
            this.position = positionName;
        }

    public int getId() {
        return id;
    }
    public String getPosition() {
        return position;
    }


    public String getName() {
        return name;
    }

    public Integer getSalary() {
        return salary;
    }



    @Override
        public String toString () {
            return "EmployeeFullInfo{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", salary=" + salary +
                    ", position='" + position + '\'' +
                    '}';
        }
    }

