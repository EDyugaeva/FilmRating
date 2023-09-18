package com.example.filmrating.dao;

import com.example.filmrating.model.Rate;

import java.util.List;
import java.util.Optional;

public interface RateDao extends DaoInterface<Rate> {

    List<Rate> findRatesByFilmId(long filmId);

    Optional<Rate> findRatesByUserIdAndFilmId(long filmId, long userId);
}
