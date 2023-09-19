package com.example.testtesttest.pojo;

import jakarta.persistence.*;
import lombok.Getter;



@Getter
@Entity
@Table(name = "app_User")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 12)
    private String login;
    @Column(nullable = false)
    private String password;
    @Enumerated (EnumType.STRING)
    @Column (nullable = false)
    private Role role;

    public AppUser(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
