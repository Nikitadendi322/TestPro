package com.example.testtesttest.pojo;

import jakarta.persistence.*;
import lombok.Getter;

import javax.management.relation.Role;

@Entity
@Table(name = "app_User")
public class AppUser {
    @Getter
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

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}
