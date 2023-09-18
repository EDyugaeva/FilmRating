package com.example.filmrating.dao;

import com.example.filmrating.model.User;

import java.util.List;

public interface UserDao extends DaoInterface<User> {
    User loginUser(String email, String password);

    boolean validateUser(String email, String password);

    List<User> searchUserByNameOrLastName(String name, String lastName);

    List<User> findTop5Users();
}
