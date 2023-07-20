package com.example.kinorate.dao;

import com.example.kinorate.model.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDao {

    public List<Film> searchFilms(String searchString) {
        Film film = null;
        List<Film> list = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnectionToDataBase();
            String sql = "SELECT * FROM films WHERE title LIKE '%" + searchString + "%'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                film = new Film();
                film.setId((long) rs.getInt("id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setRate(rs.getInt("rate"));

                list.add(film);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;


    }


    //write DAO method post film
    public int createFilm(Film film) {
        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnectionToDataBase();
            String sql = "INSERT INTO films (title, description, image) VALUES (?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, film.getTitle());
            statement.setString(2, film.getDescription());
            statement.setString(3, String.valueOf(film.getImage()));

            rowsAffected = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }
}
