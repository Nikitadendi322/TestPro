package com.example.testtesttest.DTO;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class EmployeeFullInfo {
    private String name;
    private int salary;
    private String positionName;

    public static EmployeeFullInfo fromEmployeeFullInfo(Object t) {
        return null;
    }
}
