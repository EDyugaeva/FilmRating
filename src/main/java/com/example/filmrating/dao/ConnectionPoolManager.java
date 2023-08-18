package com.example.filmrating.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;

@Getter
public class ConnectionPoolManager {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;

    static {

        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://localhost:5433/filmdatabase");
        config.setUsername("postgres");
        config.setPassword("password");
        config.setMaximumPoolSize(10);

        dataSource = new HikariDataSource(config);
    }
    private ConnectionPoolManager() {}

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
