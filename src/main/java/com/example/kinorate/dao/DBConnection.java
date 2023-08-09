package com.example.kinorate.dao;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Slf4j
public class DBConnection {

    public static Connection getConnectionToDataBase() {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5433/filmdatabase", "postgres", "password");

        } catch (ClassNotFoundException | SQLException e) {
            log.warn("Exception while connecting to DataBase");
            e.printStackTrace();
        }

        if (connection != null) {
            log.info("Connection established successfully");
        }
        return connection;

    }

}
