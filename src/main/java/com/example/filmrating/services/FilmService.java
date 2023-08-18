package com.example.filmrating.services;

import com.example.filmrating.model.Film;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FilmService {
    Optional<Film> findById(long id);
    //TODO rewrite with sending object
    int save(Film film);
    //TODO rewrite with sending object
    int update(Film film);
    void delete(long id);
    List<Film> findFilmByTitle(String searchString);
    //TODO rewrite with sending object
    int createFilm(Part filePart, String title, ServletContext context, String description) throws IOException;
    //TODO rewrite with sending object
    int updateRating(Film film);
}
