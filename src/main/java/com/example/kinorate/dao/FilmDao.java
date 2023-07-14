package com.example.kinorate.dao;

import com.example.kinorate.model.Film;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
