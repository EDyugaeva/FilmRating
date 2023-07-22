package com.example.kinorate.dao;

import com.example.kinorate.model.Film;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FilmDao {


    public Film findFilmById(Long filmId) {
        log.info("searching film by id {}", filmId);
        Film film = null;
        try {
            Connection connection = DBConnection.getConnectionToDataBase();
            String sql = "SELECT * FROM films WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, filmId);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                film = getFilmFromRS(rs);
            }

            return film;

        } catch (SQLException e) {
            log.warn("SQL exception while finding film by id");
            throw new RuntimeException("SQL exception while finding film by id");
        }
    }




    public List<Film> searchFilmsByTitle(String searchString) {
        log.info("searching film by title");
        Film film = null;
        List<Film> list = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnectionToDataBase();
            String sql = "SELECT * FROM films WHERE title LIKE '%" + searchString + "%'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                film = getFilmFromRS(rs);
                list.add(film);

            }
        } catch (SQLException e) {
            log.warn("SQL exception while finding film by title");
        }
        return list;


    }

    private static Film getFilmFromRS(ResultSet rs) throws SQLException {
        log.info("Get film from rs");

        Film film = new Film();
        film.setId((long) rs.getInt("id"));
        film.setTitle(rs.getString("title"));
        film.setDescription(rs.getString("description"));
        film.setImage(rs.getString("image"));
        film.setRate(rs.getFloat("rate"));

        log.info("return {}", film);
        return film;
    }


    //write DAO method post film
    public int createFilm(Film film) {
        log.info("Creating new film");

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
            log.warn("SQL exception while creating new film");
            e.printStackTrace();
        }
        return rowsAffected;
    }


    //find 5 films with max status
    public List<Film> findTop5Films() {
        log.info("Finding 5 top films");

        List<Film> list = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnectionToDataBase();

            String sql = "SELECT * FROM films ORDER BY rate DESC LIMIT 5";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Film film = getFilmFromRS(rs);
                list.add(film);
            }

            return list;

        } catch (SQLException e) {
            log.warn("SQL exception while finding top 5 films");
            throw new RuntimeException(e);
        }
    }

    public int updateRating(Film film) {
        log.info("Updating ratings");

        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnectionToDataBase();
            String sql = "UPDATE films SET rate = ?" +
                    "WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setFloat(1, film.getRate());
            statement.setLong(2, film.getId());

            rowsAffected = statement.executeUpdate();

        } catch (SQLException e) {
            log.warn("SQL exception while setting new rating to {}", film);
            e.printStackTrace();
        }
        return rowsAffected;
    }



}
