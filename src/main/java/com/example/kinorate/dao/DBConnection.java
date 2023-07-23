package com.example.kinorate.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnectionToDataBase() {
        Connection connection = null;

        try {

            //load the driver class for postgresql
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5433/test", "postgres", "password");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("Connection established successfully");
        }
        return connection;




    }

}
