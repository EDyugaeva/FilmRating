package com.example.kinorate.dao;

import com.example.kinorate.model.Film;
import com.example.kinorate.model.Rate;
import com.example.kinorate.model.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RateDao {

    FilmDao filmDao = new FilmDao();
    UserDao userDao = new UserDao();

    //create Rate in DB
    public int createRate(Rate rate) {
        log.info("Creating new rate: {}", rate);
        int rowAffected = 0;
        try {
            Connection connection = DBConnection.getConnectionToDataBase();

            String sql = "INSERT INTO rates (user_id, film_id, rate) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, rate.getUser().getId());
            preparedStatement.setLong(2, rate.getFilm().getId());
            preparedStatement.setInt(3, rate.getRate());

            rowAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.warn("Exception while creating new rate {}", rate);
            e.printStackTrace();
        }
        return rowAffected;
    }

    //find all rates by film id
    public List<Rate> findRatesByFilmId(long filmId) {
        List<Rate> rates = new ArrayList<>();
        try {

            Connection connection = DBConnection.getConnectionToDataBase();

            String sql = "SELECT * FROM rates WHERE film_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, filmId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                rates.add(getRateFromRS(rs));
            }

            return rates;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    //find all rates by user id
    public List<Rate> findRatesByUserId(long filmId) {
        List<Rate> rates = new ArrayList<>();
        try {

            Connection connection = DBConnection.getConnectionToDataBase();

            String sql = "SELECT * FROM rates WHERE user_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, filmId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                rates.add(getRateFromRS(rs));
            }

            return rates;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private Rate getRateFromRS(ResultSet rs) throws SQLException {
        log.info("get rate");

        Rate rate = new Rate();
        rate.setId(rs.getLong("id"));
        rate.setRate(rs.getInt("rate"));

        Film film = filmDao.findFilmById(rs.getLong("film_id"));
        User user = userDao.findUserById(rs.getLong("user_id"));

        rate.setFilm(film);
        rate.setUser(user);

        return rate;

    }

    public Rate findRatesByUserIdAndFilmId(long filmId, long userId) {
        log.info("Searching for rate with user id = {} and film id = {}", userId, filmId);
        Rate rate = null;
        try {
            Connection connection = DBConnection.getConnectionToDataBase();

            String sql = "SELECT * FROM rates WHERE user_id = ? AND film_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, filmId);


            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                rate = getRateFromRS(rs);
                log.warn(rate.toString());
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
        try {
            Connection connection = DBConnection.getConnectionToDataBase();

            String sql = "UPDATE  rates set user_id = ?, film_id = ?, rate = ? where id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, rate.getUser().getId());
            preparedStatement.setLong(2, rate.getFilm().getId());
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
