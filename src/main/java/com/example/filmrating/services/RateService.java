package com.example.filmrating.services;

import com.example.filmrating.model.Rate;

import java.util.List;
import java.util.Optional;

public interface RateService {

    List<Rate> findRatesByFilmId(long filmId);

    void setRating(Rate rate);

    Optional<Rate> findRatesByUserIdAndFilmId(long filmId, long userId);

    int save(Rate rate);

    int update(Rate rate);
}
