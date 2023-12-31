package com.example.filmrating.dao.impl;

import com.example.filmrating.dao.ConnectionPoolManager;
import com.example.filmrating.dao.FilmDao;
import com.example.filmrating.dao.mapper.FilmMapper;
import com.example.filmrating.model.Film;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class FilmDaoImpl implements FilmDao {

    private static final String FIND_BY_ID = "SELECT f.id as filmId, f.title, f.description, f.image, f.rate, " +
            "r.id as rate_id, r.user_id as r_user_id, r.rate as r_rate FROM films as f  full join comments c on f.id = c.film_id " +
            "full join rates r on f.id = r.film_id WHERE f.id=?";
    private static final String FIND_BY_TITLE = "SELECT f.id as filmId, f.title, f.description, f.image, f.rate, " +
            "r.id as rate_id, r.user_id as r_user_id, r.rate as r_rate FROM films as f  full join comments c on f.id = c.film_id " +
            "full join rates r on f.id = r.film_id WHERE f.title ILIKE ?";
    private static final String INSERT = "INSERT INTO films (title, description, image) VALUES (?, ?, ?)";
    private static final String FIND_TOP_5 = "SELECT f.id as filmId, f.title, f.description, f.image, f.rate from films f ORDER BY f.rate DESC LIMIT 5";
    private static final String UPDATE_RATING = "UPDATE films SET rate = ? WHERE id = ?";
    private static final String DELETE_FILM_BY_ID = "DELETE from films WHERE id = ?";
    private static final String FIND_ALL = "SELECT f.id as filmId, f.title, f.description, f.image, f.rate " +
            "from films f";
    private static final String UPDATE = "UPDATE films SET title = ?, description = ?, image = ? where id = ?";
    private final FilmMapper mapper = new FilmMapper();
    Connection connection;

    {
        try {
            connection = ConnectionPoolManager.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Film> findById(long filmId) {
        log.info("searching film by id {}", filmId);
        Film film = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {
            preparedStatement.setLong(1, filmId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                film = mapper.getFilm(rs);
            }
        } catch (SQLException e) {
            log.warn("SQL exception while finding film by id", e);
        }
        return Optional.ofNullable(film);
    }

    @Override
    public List<Film> searchFilmsByTitle(String searchString) {
        log.info("searching film by title");
        Film film = null;
        List<Film> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_TITLE,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {
            statement.setString(1, "%" + searchString + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                film = mapper.getFilm(rs);
                System.out.println(film);
                list.add(film);
            }
            log.info("Film was found");
        } catch (SQLException e) {
            log.warn("SQL exception while finding film by title", e);
        }
        return list;
    }

    @Override
    public int save(Film film) {
        log.info("Creating new film");
        int rowsAffected = 0;
        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, film.getTitle());
            statement.setString(2, film.getDescription());
            statement.setString(3, String.valueOf(film.getImage()));
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            log.warn("SQL exception while creating new film", e);
        }
        return rowsAffected;
    }

    @Override
    public List<Film> getAll() {
        log.info("Finding all films");
        List<Film> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Film film = mapper.getFilmFromRS(rs);
                list.add(film);
            }
        } catch (SQLException e) {
            log.warn("SQL exception while finding all films", e);
        }
        return list;
    }

    @Override
    public int update(Film obj) {
        log.info("Updating film with id = {}", obj.getId());
        int rowsAffected = 0;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, obj.getTitle());
            statement.setString(2, obj.getDescription());
            statement.setString(3, obj.getImage());
            statement.setLong(4, obj.getId());
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            log.warn("SQL exception while setting new rating to {}", obj, e);
        }
        return rowsAffected;
    }

    @Override
    public List<Film> findTop5Films() {
        log.info("Finding 5 top films");
        List<Film> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_TOP_5)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Film film = mapper.getFilmFromRS(rs);
                log.info("Film with id = {} is in top", film.getId());
                list.add(film);
            }
        } catch (SQLException e) {
            log.warn("SQL exception while finding top 5 films", e);
        }
        return list;
    }

    @Override
    public int updateRating(Film film) {
        log.info("Updating ratings");
        int rowsAffected = 0;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_RATING)) {
            statement.setFloat(1, film.getRate());
            statement.setLong(2, film.getId());
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            log.warn("SQL exception while setting new rating to {}", film, e);
        }
        return rowsAffected;
    }

    @Override
    public void delete(long id) {
        log.info("Deleting film by id = {}", id);
        try (PreparedStatement statement = connection.prepareStatement(DELETE_FILM_BY_ID)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.warn("SQL exception while deleting film with id =  {}", id, e);
        }
    }
}
