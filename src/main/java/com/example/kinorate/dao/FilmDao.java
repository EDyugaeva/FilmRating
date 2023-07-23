package com.example.kinorate.dao;

import com.example.kinorate.dao.mapper.FilmMapper;
import com.example.kinorate.model.Film;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FilmDao {

    private final FilmMapper mapper = new FilmMapper();
    private static final String FIND_BY_ID = "SELECT * FROM films WHERE id = ?";
    private static final String FIND_BY_TITLE = "SELECT * FROM films WHERE title LIKE ?";
    private static final String INSERT = "INSERT INTO films (title, description, image) VALUES (?, ?, ?)";
    private static final String FIND_TOP_5 = "SELECT * FROM films ORDER BY rate DESC LIMIT 5";
    private static final String UPDATE_RATING = "UPDATE films SET rate = ? WHERE id = ?";



    public Film findFilmById(Long filmId) {
        log.info("searching film by id {}", filmId);
        Film film = null;
        try {
            Connection connection = DBConnection.getConnectionToDataBase();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setLong(1, filmId);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                film = mapper.getFilm(rs);
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
            PreparedStatement statement = connection.prepareStatement(FIND_BY_TITLE);
            statement.setString(1, "%" + searchString + "%");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                film = mapper.getFilm(rs);
                list.add(film);

            }
        } catch (SQLException e) {
            log.warn("SQL exception while finding film by title");
        }
        return list;


    }


    public int createFilm(Film film) {
        log.info("Creating new film");

        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnectionToDataBase();

            PreparedStatement statement = connection.prepareStatement(INSERT);
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


    public List<Film> findTop5Films() {
        log.info("Finding 5 top films");
        List<Film> list = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnectionToDataBase();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_TOP_5);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Film film = mapper.getFilm(rs);
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

            PreparedStatement statement = connection.prepareStatement(UPDATE_RATING);
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
