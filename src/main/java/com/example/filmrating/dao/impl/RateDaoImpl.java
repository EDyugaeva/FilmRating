package com.example.filmrating.dao.impl;

import com.example.filmrating.dao.DBConnection;
import com.example.filmrating.dao.RateDao;
import com.example.filmrating.dao.mapper.RateMapper;
import com.example.filmrating.model.Rate;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class RateDaoImpl implements RateDao {

    Connection connection = DBConnection.getConnectionToDataBase();

    private final RateMapper mapper = new RateMapper();
    private final static String INSERT = "INSERT INTO rates (user_id, film_id, rate) VALUES (?, ?, ?)";
    private final static String FIND_BY_FILM_ID = "SELECT * FROM rates WHERE film_id = ?";
    private final static String FIND_BY_USER_ID_AND_FILM_ID = "SELECT * FROM rates WHERE user_id = ? AND film_id = ?";
    private final static String UPDATE = "UPDATE  rates set user_id = ?, film_id = ?, rate = ? where id = ?";
    private static final String FIND_ALL = "SELECT * from rates";
    private static final String DELETE_BY_ID = "DELETE from rates where id = ?";
    private static final String FIND_BY_ID = "SELECT * from comments where id =?";

    @Override
    public int save(Rate rate) {
        log.info("Creating new rate: {}", rate);
        int rowAffected = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {

            preparedStatement.setLong(1, rate.getUser());
            preparedStatement.setLong(2, rate.getFilm());
            preparedStatement.setInt(3, rate.getRate());

            rowAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.warn("Exception while creating new rate {}", rate);
            e.printStackTrace();
        }
        return rowAffected;
    }

    @Override
    public List<Rate> findRatesByFilmId(long filmId) {
        log.info("Searching for rates with film id = {}", filmId);
        List<Rate> rates = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_FILM_ID)) {

            preparedStatement.setLong(1, filmId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                rates.add(mapper.getRate(rs));

            }

        } catch (SQLException e) {
            log.warn("Exception while Searching for rates with film id = {}", filmId);
            e.printStackTrace();
        }
        return rates;

    }

    @Override
    public Optional<Rate> findRatesByUserIdAndFilmId(long filmId, long userId) {
        log.info("Searching for rate with user id = {} and film id = {}", userId, filmId);
        Rate rate = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER_ID_AND_FILM_ID)) {

            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, filmId);


            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                rate = mapper.getRate(rs);
            }

        } catch (SQLException e) {
            log.warn("Exception while searching for rate with user id = {} and film id = {}", userId, filmId);
            e.printStackTrace();
        }
        return Optional.ofNullable(rate);

    }

    @Override
    public int update(Rate rate) {
        log.info("Updating rate with id = {}", rate.getId());
        int rowAffected = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setLong(1, rate.getUser());
            preparedStatement.setLong(2, rate.getFilm());
            preparedStatement.setInt(3, rate.getRate());
            preparedStatement.setLong(4, rate.getId());

            rowAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.warn("Exception while update existing rate with id = {}", rate.getId());
            e.printStackTrace();
        }

        return rowAffected;
    }

    @Override
    public Optional<Rate> findById(long id) {
        log.info("searching rate by id {}", id);
        Rate rate = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                rate  = mapper.getRate(rs);
            }

        } catch (SQLException e) {
            log.warn("SQL exception while finding rate by id");
            e.printStackTrace();
        }

        return Optional.ofNullable(rate);
    }

    @Override
    public List<Rate> getAll() {
        log.info("searching for all rates");
        List<Rate> rates = new ArrayList<>();
        Rate rate = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                rate = mapper.getRate(rs);
                rates.add(rate);
            }

        } catch (SQLException e) {
            log.warn("SQL exception while comments");
            e.printStackTrace();
        }

        return rates;
    }

    @Override
    public void delete(long id) {
        log.info("Deleting rates by id = {}", id);
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.warn("SQL exception while deleting rate with id =  {}", id);
            e.printStackTrace();
        }

    }
}
