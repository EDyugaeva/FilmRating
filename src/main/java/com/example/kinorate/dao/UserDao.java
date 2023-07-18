package com.example.kinorate.dao;

import com.example.kinorate.model.Film;
import com.example.kinorate.model.Role;
import com.example.kinorate.model.User;

import java.sql.*;

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

    //authorisation user method
    public User loginUser(String email, String password) {
        User user = null;
        try {
            Connection connection = DBConnection.getConnectionToDataBase();
            String selectQuery = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User();

                user.setId((long) resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setBirthDate(resultSet.getDate("birth_date").toLocalDate());

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}
