package com.example.filmrating.services;

import com.example.filmrating.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(long id);

    int save(User user);

    int update(User user);

    void delete(long id);

    List<User> findTop5Users();

    List<User> findUserByNameOrLastName(String searchString);

    User loginUser(String email, String password);

    boolean validateUser(String email, String password);
}
