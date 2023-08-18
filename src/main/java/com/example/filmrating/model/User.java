package com.example.filmrating.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
public class User {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthDate;
    private Role role;

    private int status;
    private boolean isBanned;
    public User() {
    }

    public User(String name, String lastName, String email, String password, LocalDate birthDate) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.role = Role.USER;
        this.status = 1;
        this.isBanned = false;


    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", role=" + role +
                ", status=" + status +
                ", isBanned=" + isBanned +
                '}';
    }
}