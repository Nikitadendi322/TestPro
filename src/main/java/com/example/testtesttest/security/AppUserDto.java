package com.example.testtesttest.security;

import lombok.Getter;

import com.example.testtesttest.pojo.Role;

@Getter
public class AppUserDto {
    private int id;
    private String login;
    private String password;
    private Role role;

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
    public void getRole(Role role){
        this.role=role;
    }
}
