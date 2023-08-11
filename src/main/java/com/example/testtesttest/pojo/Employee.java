package com.example.testtesttest.pojo;

import com.example.testtesttest.DTO.EmployeeFullInfo;
import jakarta.persistence.*;
import lombok.*;


@Builder(toBuilder = true)

@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Data
@Table(name = "employee")
public class Employee extends EmployeeFullInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int salary;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_position")
    private Position position;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_report")
    private Report report;
}
