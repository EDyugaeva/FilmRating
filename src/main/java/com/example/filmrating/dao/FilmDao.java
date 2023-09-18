package com.example.filmrating.dao;

import com.example.filmrating.model.Film;

import java.util.List;

public interface FilmDao extends DaoInterface<Film> {
    List<Film> searchFilmsByTitle(String searchString);

    List<Film> findTop5Films();

    int updateRating(Film film);
}
