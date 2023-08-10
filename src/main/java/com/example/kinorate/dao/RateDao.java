package com.example.kinorate.dao;

import com.example.kinorate.model.Rate;

import java.util.List;

public interface RateDao extends DaoInterface<Rate> {

    List<Rate> findRatesByFilmId(long filmId);
    Rate findRatesByUserIdAndFilmId(long filmId, long userId);
}
