package com.example.kinorate.dao;

import com.example.kinorate.model.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    public int registerUser(User user) {

        int rowsAffected = 0;
        try {

            //get connection to database
            Connection connection = DBConnection.getConnectionToDataBase();

            String insertQuery = "INSERT INTO users (name, last_name, email, password, birth_date) VALUES (?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setDate(5, Date.valueOf(user.getBirthDate()));

            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsAffected;


    }
}
