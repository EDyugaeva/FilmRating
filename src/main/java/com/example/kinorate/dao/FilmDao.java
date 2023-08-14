package com.example.kinorate.dao;

import com.example.kinorate.model.Film;

import java.util.List;

public interface FilmDao extends DaoInterface<Film> {
    List<Film> searchFilmsByTitle(String searchString);
    List<Film> findTop5Films();
    int updateRating(Film film);

}
