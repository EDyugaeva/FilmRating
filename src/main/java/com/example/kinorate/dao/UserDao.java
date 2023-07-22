package com.example.kinorate.dao;

import com.example.kinorate.model.Role;
import com.example.kinorate.model.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class UserDao {

    public int registerUser(User user) {
        log.info("register new user");
        int rowsAffected = 0;
        try {
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
            log.warn("exception during saving new user in database");
            e.printStackTrace();
        }
        return rowsAffected;
    }

    public User loginUser(String email, String password) {
        log.info("login user with email = {}", email);
        User user = null;
        try {
            ResultSet resultSet = getPreparedStatementForValidatingUser(email, password).executeQuery();
            while (resultSet.next()) {
                user = getUser(resultSet);
            }

        } catch (SQLException e) {
            log.warn("exception during login");
            e.printStackTrace();
        }
        return user;
    }

    private static PreparedStatement getPreparedStatementForValidatingUser(String email, String password) throws SQLException {
        log.info("Prepared statement for validation");
        Connection connection = DBConnection.getConnectionToDataBase();

        String selectQuery = "SELECT * FROM users WHERE email = ? AND password = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        return preparedStatement;
    }

    //validation method
    public boolean validateUser(String email, String password) {
        log.info("user validation with email {} ", email);
        try {

            ResultSet resultSet = getPreparedStatementForValidatingUser(email, password).executeQuery();

            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            log.warn("exception during user validation");
            e.printStackTrace();
        }
        return false;
    }

    public List<User> searchUserByNameAndLastName(String name, String lastName) {
        log.info("Searching for user with name = {} and last name = {}", name, lastName);
        User user = null;
        List<User> list = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnectionToDataBase();
            String sql = "SELECT * FROM users WHERE name LIKE '%" + name + "%' AND last_name LIKE '%" + lastName + "%'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                user = getUser(rs);
                list.add(user);

            }
        } catch (SQLException e) {
            log.warn("exception searching user");
            e.printStackTrace();
        }

        return list;


    }

    private User getUser(ResultSet rs) throws SQLException {
        User user;
        user = new User();
        user.setId((long) rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setBirthDate(rs.getDate("birth_date").toLocalDate());
        user.setRole(Role.valueOf(rs.getString("role")));
        user.setStatus(Integer.parseInt(rs.getString("status")));
        user.setBanned(rs.getBoolean("isBanned"));
        return user;
    }

    //find user by id
    public User findUserById(Long id) {
        log.info("Searching user with id = {}", id);
        User user = null;
        try {
            Connection connection = DBConnection.getConnectionToDataBase();

            String sql = "SELECT * FROM users WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = getUser(rs);
            }

            return user;

        } catch (SQLException e) {
            log.warn("exception searching user with id = {}", id);
            e.printStackTrace();
        }
        return user;
    }

    //find 5 Users with max status
    public List<User> findTop5Users() {
        log.info("Finding top 5 users");
        List<User> list = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnectionToDataBase();

            String sql = "SELECT * FROM users ORDER BY status DESC LIMIT 5";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = getUser(rs);
                list.add(user);
            }

            return list;

        } catch (SQLException e) {
            log.warn("Exception during getting top 5 users");

            e.printStackTrace();        }
        return list;
    }
    public int updateUser(User user) {
        log.info("Updating user with id = {}", user.getId());

        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnectionToDataBase();
            String sql = "UPDATE users SET name = ?, last_name = ?, email = ?, " +
                    "password = ?, birth_date = ?,  status = ?, isbanned = ? where id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setDate(5, Date.valueOf(user.getBirthDate()));


            statement.setInt(6, user.getStatus());
            statement.setBoolean(7, user.isBanned());
            statement.setLong(8, user.getId());

            rowsAffected = statement.executeUpdate();

        } catch (SQLException e) {
            log.warn("SQL exception while setting new status to user {}", user);
            e.printStackTrace();
        }
        return rowsAffected;
    }


}
