package com.example.kinorate.dao;

import com.example.kinorate.model.Film;
import com.example.kinorate.model.Rate;
import com.example.kinorate.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RateDao {

    //create Rate in DB
    public int createRate(Rate rate) {
        try {
            Connection connection = DBConnection.getConnectionToDataBase();

            String sql = "INSERT INTO rates (user_id, film_id, rate) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, rate.getUser().getId());
            preparedStatement.setLong(2, rate.getFilm().getId());
            preparedStatement.setInt(3, rate.getRate());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        FilmDao filmDao = new FilmDao();
        UserDao userDao = new UserDao();

        Rate rate = new Rate();
        rate.setId(rs.getLong("id"));
        rate.setRate(rs.getInt("rate"));

        Film film = filmDao.findFilmById(rs.getLong("film_id"));
        User user = userDao.findUserById(rs.getLong("user_id"));

        rate.setFilm(film);
        rate.setUser(user);
        return rate;

    }

}
