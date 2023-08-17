package com.example.filmrating.services;

import com.example.filmrating.dao.DaoFactory;
import com.example.filmrating.exceptions.IllegalImageFormatException;
import com.example.filmrating.model.Film;
import com.example.filmrating.utills.FileUtills;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Slf4j
public class FilmService {

    public static Optional<Film> findById(long id) {
        return DaoFactory.getInstance().getFilmDao().findById(id);
    }

    public static int save(Film film) {

        return DaoFactory.getInstance().getFilmDao().save(film);
    }

    public static int update(Film film)  {
        return DaoFactory.getInstance().getFilmDao().update(film);
    }

    public static void delete(long id) {
        Film film = FilmService.findById(id).orElseThrow(() -> new NoSuchElementException("There is no films with that id"));
        DaoFactory.getInstance().getFilmDao().delete(id);
        FileUtills.deleteFile(film.getImage());

    }

    public static List<Film> findTop5Films() {
        return DaoFactory.getInstance().getFilmDao().findTop5Films();
    }

    public static List<Film> findFilmByTitle(String searchString) {
        return DaoFactory.getInstance().getFilmDao().searchFilmsByTitle(searchString);

    }

    public static int createFilm(Part filePart, String title, ServletContext context, String description ) throws IOException {
        if (!isImage(filePart, context)) {
            log.warn("Error, file is not an image");
            throw new IllegalImageFormatException("File is not an image");
        }

        String type = "films";

        String path = FileUtills.saveImage(filePart, title, type, context.getRealPath("/images/" + type));

        Film film = new Film(title, description, path);

        int affectedRow = FilmService.save(film);
        return affectedRow;

    }

    private static boolean isImage(Part part, ServletContext context) {
        String mimeType = context.getMimeType(part.getSubmittedFileName());
        return mimeType != null && mimeType.startsWith("image/");
    }

    public static int updateRating(Film film) {
        return DaoFactory.getInstance().getFilmDao().updateRating(film);
    }
}
