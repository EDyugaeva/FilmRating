package com.example.filmrating.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
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
}
