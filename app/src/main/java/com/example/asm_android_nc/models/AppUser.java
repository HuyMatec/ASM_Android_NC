package com.example.asm_android_nc.models;

import java.io.Serializable;

public class AppUser implements Serializable {
    private Integer id,role;
    private String email,password,name;

    public AppUser() {
    }

    public AppUser(Integer id, Integer role, String email, String password, String name) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        String pattern = "";
        if (!email.matches(pattern)){
            throw new Exception("Không đúng định dạng");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
