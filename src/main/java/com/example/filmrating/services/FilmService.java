package com.example.filmrating.services;

import com.example.filmrating.model.Film;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FilmService {
    Optional<Film> findById(long id);

    int save(Film film);

    int update(Film film);

    void delete(long id);

    List<Film> findTop5Films();

    List<Film> findFilmByTitle(String searchString);

    int createFilm(Part filePart, String title, ServletContext context, String description) throws IOException;

    int updateRating(Film film);
}
