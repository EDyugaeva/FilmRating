package com.example.filmrating.dao.impl;

import com.example.filmrating.dao.ConnectionPoolManager;
import com.example.filmrating.dao.UserDao;
import com.example.filmrating.dao.mapper.UserMapper;
import com.example.filmrating.exceptions.IllegalImageFormatException;
import com.example.filmrating.model.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class UserDaoImpl implements UserDao {
    private static final String INSERT = "INSERT INTO users (name, last_name, email, password, birth_date) VALUES (?,?,?,?,?)";
    private static final String FIND_BY_PASSWORD_AND_EMAIL = "SELECT * FROM users WHERE email = ? AND password = ?";
    private static final String FIND_BY_NAME_OR_LAST_NAME = "SELECT * FROM users WHERE name ILIKE ? OR last_name ILIKE ?";
    private static final String FIND_BY_ID = "SELECT u.id as id, u.name, u.last_name, u.email, u.password, u.birth_date, u.role, u.status, u.isbanned from users u " +
            "WHERE u.id = ?";
    private static final String FIND_TOP_5 = "SELECT * FROM users ORDER BY status DESC LIMIT 5";
    private static final String FIND_ALL = "SELECT * FROM users";
    private static final String UPDATE = "UPDATE users SET name = ?, last_name = ?, email = ?, password = ?, birth_date = ?, " +
            " status = ?, isbanned = ? where id = ?";
    private static final String DELETE = "DELETE  from users WHERE id = ?";
    private final UserMapper mapper = new UserMapper();
    Connection connection;

    {
        try {
            connection = ConnectionPoolManager.getConnection();
        } catch (SQLException e) {
            throw new IllegalImageFormatException("hfajkhfjkaehlf");
        }
    }

    @Override
    public int save(User user) {
        log.info("register new user");
        int rowsAffected = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setDate(5, Date.valueOf(user.getBirthDate()));
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.warn("exception during saving new user in database", e);
        }
        return rowsAffected;
    }

    @Override
    public User loginUser(String email, String password) {
        log.info("login user with email = {}", email);
        User user = null;
        try (ResultSet resultSet = getPreparedStatementForValidatingUser(email, password).executeQuery()) {
            while (resultSet.next()) {
                user = mapper.getUserDetails(resultSet);
            }
        } catch (SQLException e) {
            log.warn("exception during login", e);
        }
        return user;
    }

    private PreparedStatement getPreparedStatementForValidatingUser(String email, String password) throws SQLException {
        log.info("Prepared statement for validation");
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PASSWORD_AND_EMAIL);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        return preparedStatement;
    }

    @Override
    public boolean validateUser(String email, String password) {
        log.info("user validation with email {} ", email);
        try (ResultSet resultSet = getPreparedStatementForValidatingUser(email, password).executeQuery()) {
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            log.warn("exception during user validation", e);
        }
        return false;
    }

    @Override
    public List<User> searchUserByNameOrLastName(String name, String lastName) {
        log.info("Searching for user with name = {} and last name = {}", name, lastName);
        User user;
        List<User> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME_OR_LAST_NAME)) {
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, lastName);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = mapper.getUserDetails(rs);
                list.add(user);
            }
        } catch (SQLException e) {
            log.warn("exception searching user", e);
        }
        return list;
    }

    @Override
    public Optional<User> findById(long id) {
        log.info("Searching user with id = {}", id);
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = mapper.getUserDetails(rs);
            }
        } catch (SQLException e) {
            log.warn("exception searching user with id = {}", id, e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAll() {
        log.info("Finding all users");
        List<User> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = mapper.getUserDetails(rs);
                list.add(user);
            }
        } catch (SQLException e) {
            log.warn("Exception during getting all users", e);
        }
        return list;
    }

    @Override
    public List<User> findTop5Users() {
        log.info("Finding top 5 users");
        List<User> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_TOP_5)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = mapper.getUserDetails(rs);
                list.add(user);
            }
        } catch (SQLException e) {
            log.warn("Exception during getting top 5 users", e);
        }
        return list;
    }

    @Override
    public int update(User user) {
        log.info("Updating user with id = {}", user.getId());
        int rowsAffected = 0;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
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
            log.warn("SQL exception while setting new status to user {}", user, e);
        }
        return rowsAffected;
    }

    @Override
    public void delete(long id) {
        log.info("Deleting user by id = {}", id);
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.warn("SQL exception while deleting user with id =  {}", id, e);
        }
    }
}
