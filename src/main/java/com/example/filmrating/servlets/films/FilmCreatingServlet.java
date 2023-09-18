package com.example.filmrating.servlets.films;

import com.example.filmrating.exceptions.IllegalImageFormatException;
import com.example.filmrating.services.FilmService;
import com.example.filmrating.services.impl.FilmServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet("/film-creating")
@Slf4j
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 20 * 1024 * 1024)
public class FilmCreatingServlet extends HttpServlet {

    private static final FilmService filmService = new FilmServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Creating new film");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Part filePart = req.getPart("file");
        RequestDispatcher dispatcher;

        try {
            int affectedRow = filmService.createFilm(filePart, title, getServletContext(), description);
            if (affectedRow == 1) {
                log.info("New film is saved");
                req.setAttribute("info", "New film is saved!");
                dispatcher = req.getRequestDispatcher("html/OK.jsp");
                dispatcher.include(req, resp);
            } else {
                log.warn("Error while saving film, maybe it is saved before");
                req.setAttribute("error", "Maybe film was saved before");
                dispatcher = req.getRequestDispatcher("html/error.jsp");
                dispatcher.include(req, resp);
            }
        } catch (IllegalImageFormatException e) {
            log.warn("Format of file is not image", e);
            req.setAttribute("error", "File must be an image");
            dispatcher = req.getRequestDispatcher("html/error.jsp");
            dispatcher.include(req, resp);
        } catch (IOException e) {
            log.warn("Error while saving film", e);
            req.setAttribute("error", "Exception while saving file");
            dispatcher = req.getRequestDispatcher("html/error.jsp");
            dispatcher.include(req, resp);
        }
        dispatcher = req.getRequestDispatcher("html/filmcreating.jsp");
        dispatcher.include(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("html/filmcreating.jsp");
        dispatcher.include(req, resp);
    }
}
