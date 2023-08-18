package com.example.filmrating.services.impl;

import com.example.filmrating.dao.DaoFactory;
import com.example.filmrating.dao.FilmDao;
import com.example.filmrating.exceptions.IllegalImageFormatException;
import com.example.filmrating.model.Film;
import com.example.filmrating.services.FilmService;
import com.example.filmrating.utills.FileUtills;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
public class FilmServiceImpl  implements FilmService{

    private static final FilmDao filmDao = DaoFactory.getInstance().getFilmDao();

    public  Optional<Film> findById(long id) {
        return  filmDao.findById(id);
    }

    public  int save(Film film) {
        return  filmDao.save(film);
    }

    public  int update(Film film) {
        return  filmDao.update(film);
    }

    public  void delete(long id) {
        Film film = findById(id).orElseThrow(() -> new NoSuchElementException("There is no films with that id"));
        filmDao.delete(id);
        FileUtills.deleteFile(film.getImage());

    }

    public  List<Film> findTop5Films() {
        return  filmDao.findTop5Films();
    }

    public  List<Film> findFilmByTitle(String searchString) {
        return  filmDao.searchFilmsByTitle(searchString);

    }

    public  int createFilm(Part filePart, String title, ServletContext context, String description) throws IOException {
        if (!isImage(filePart, context)) {
            log.warn("Error, file is not an image");
            throw new IllegalImageFormatException("File is not an image");
        }

        String type = "films";

        String path = FileUtills.saveImage(filePart, title, type, context.getRealPath("/images/" + type));

        Film film = new Film(title, description, path);

        int affectedRow = save(film);
        return affectedRow;

    }

    private  boolean isImage(Part part, ServletContext context) {
        String mimeType = context.getMimeType(part.getSubmittedFileName());
        return mimeType != null && mimeType.startsWith("image/");
    }

    public  int updateRating(Film film) {
        return  filmDao.updateRating(film);
    }
}
