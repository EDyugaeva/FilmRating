package com.example.kinorate.dao;

import com.example.kinorate.dao.mapper.UserMapper;
import com.example.kinorate.model.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserDao {

    private final UserMapper mapper = new UserMapper();

    private static final String INSERT = "INSERT INTO users (name, last_name, email, password, birth_date) VALUES (?,?,?,?,?)";
    private static final String FIND_BY_PASSWORD_AND_EMAIL = "SELECT * FROM users WHERE email = ? AND password = ?";
    private static final String FIND_BY_NAME_OR_LAST_NAME = "SELECT * FROM users WHERE name LIKE ? OR last_name LIKE ?";

    private static final String FIND_BY_ID = "SELECT * FROM users WHERE id = ?";

    private static final String FIND_TOP_5 = "SELECT * FROM users ORDER BY status DESC LIMIT 5";

    private static final String UPDATE = "UPDATE users SET name = ?, last_name = ?, email = ?, password = ?, birth_date = ?, " +
            " status = ?, isbanned = ? where id = ?";


    public int registerUser(User user) {
        log.info("register new user");
        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnectionToDataBase();

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);

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
                user = mapper.getUser(resultSet);
            }

        } catch (SQLException e) {
            log.warn("exception during login");
            e.printStackTrace();
        }
        return user;
    }

    private PreparedStatement getPreparedStatementForValidatingUser(String email, String password) throws SQLException {
        log.info("Prepared statement for validation");
        Connection connection = DBConnection.getConnectionToDataBase();

        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PASSWORD_AND_EMAIL);

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

    public List<User> searchUserByNameOrLastName(String name, String lastName) {
        log.info("Searching for user with name = {} and last name = {}", name, lastName);
        User user = null;
        List<User> list = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnectionToDataBase();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME_OR_LAST_NAME);

            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, "%" + lastName + "%");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                user = mapper.getUser(rs);
                list.add(user);

            }
        } catch (SQLException e) {
            log.warn("exception searching user");
            e.printStackTrace();
        }

        return list;
    }


    public User findUserById(Long id) {
        log.info("Searching user with id = {}", id);
        User user = null;
        try {
            Connection connection = DBConnection.getConnectionToDataBase();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                user = mapper.getUser(rs);
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

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_TOP_5);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = mapper.getUser(rs);
                list.add(user);
            }

        } catch (SQLException e) {
            log.warn("Exception during getting top 5 users");

            e.printStackTrace();
        }
        return list;
    }

    public int updateUser(User user) {
        log.info("Updating user with id = {}", user.getId());
        int rowsAffected = 0;

        try {
            Connection connection = DBConnection.getConnectionToDataBase();

            PreparedStatement statement = connection.prepareStatement(UPDATE);
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
