package com.example.kinorate.dao;

import com.example.kinorate.dao.mapper.RateMapper;
import com.example.kinorate.model.Rate;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RateDao {

    Connection connection = DBConnection.getConnectionToDataBase();

    private final RateMapper mapper = new RateMapper();
    private final static String INSERT = "INSERT INTO rates (user_id, film_id, rate) VALUES (?, ?, ?)";
    private final static String FIND_BY_FILM_ID = "SELECT * FROM rates WHERE film_id = ?";
    private final static String FIND_BY_USER_ID = "SELECT * FROM rates WHERE user_id = ?";
    private final static String FIND_BY_USER_ID_AND_FILM_ID = "SELECT * FROM rates WHERE user_id = ? AND film_id = ?";
    private final static String UPDATE = "UPDATE  rates set user_id = ?, film_id = ?, rate = ? where id = ?";

    //create Rate in DB
    public int createRate(Rate rate) {
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


    //find all rates by user id
    public List<Rate> findRatesByUserId(long userId) {
        log.info("Searching for rates with user id = {}", userId);
        List<Rate> rates = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER_ID)){

            preparedStatement.setLong(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                rates.add(mapper.getRate(rs));
            }

        } catch (SQLException e) {
            log.warn("Exception while Searching for rates with user id = {}", userId);
            e.printStackTrace();
        }
        return rates;

    }


    public Rate findRatesByUserIdAndFilmId(long filmId, long userId) {
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
        return rate;

    }

    public int updateRate(Rate rate) {
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
}
