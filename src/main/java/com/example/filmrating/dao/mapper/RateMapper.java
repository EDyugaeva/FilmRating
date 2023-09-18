package com.example.filmrating.dao.mapper;

import com.example.filmrating.model.Rate;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class RateMapper {

    public Rate getRate(ResultSet resultSet) {
        log.info("Mapping rate from the result set");
        Rate rate = null;
        try {
            rate = new Rate();

            rate.setId(resultSet.getLong("id"));
            rate.setRate(resultSet.getInt("rate"));

            rate.setFilm(resultSet.getLong("film_id"));
            rate.setUser(resultSet.getLong("user_id"));
        } catch (SQLException e) {
            log.warn("Exception while mapping rate rs", e);
        }
        return rate;
    }
}
