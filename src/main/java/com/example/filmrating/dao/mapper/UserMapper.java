package com.example.filmrating.dao.mapper;

import com.example.filmrating.model.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserMapper {

    public User getUserDetails(ResultSet resultSet) {
        User user = null;
        log.info("Mapping rs to find a user");
        try {
            user = new User();
            long id = getUser(resultSet, user);
            log.info("User with id = {} is found ", id);


        } catch (SQLException e) {
            log.warn("Exception while mapping user rs");
            e.printStackTrace();
        }

        return user;
    }

    private long getUser(ResultSet resultSet, User user) throws SQLException {
        long id = resultSet.getLong("id");
        user.setId(id);
        user.setName(resultSet.getString("name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
        user.setRole(Role.valueOf(resultSet.getString("role")));
        user.setStatus(Integer.parseInt(resultSet.getString("status")));
        user.setBanned(resultSet.getBoolean("isBanned"));
        return id;
    }



}
