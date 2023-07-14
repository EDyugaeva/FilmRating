package com.example.kinorate.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class User {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Date birthDate;
    private Role role;
    private List<Comment> commentsList;
    private List<Rate> ratesList;
    private int status;
    private boolean isBanned;

}
