package com.example.testtesttest.security;



import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity(name = "role1")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int id_role;

    @Column(nullable = false, unique = true)
    private String role;
}
