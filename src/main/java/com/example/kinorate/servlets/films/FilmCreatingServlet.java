package com.example.kinorate.servlets.films;

import com.example.kinorate.dao.FilmDao;
import com.example.kinorate.model.Film;
import com.example.kinorate.utills.SavingFiles;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;


@WebServlet("/filmCreating")
@Slf4j
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 20 * 1024 * 1024)
public class FilmCreatingServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("Creating new film");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Part filePart = req.getPart("file");
        RequestDispatcher dispatcher;

        if (!isImage(filePart)) {
            log.warn("Error, file is not an image");
            req.setAttribute("error", "File must be an image");
            dispatcher = req.getRequestDispatcher("html/error.jsp");
            dispatcher.include(req, resp);
            return;
        }
        String type = "films";

//         Save the file to the server
        String path = SavingFiles.saveImage(filePart, title, type, getServletContext().getRealPath("/images/" + type));
        if (path == null) {
            log.warn("Error during file upload");
            dispatcher = req.getRequestDispatcher("html/error.jsp");
            req.setAttribute("error", "Error during file upload");
            dispatcher.include(req, resp);
            return;
        }

        FilmDao dao = new FilmDao();
        Film film = new Film(title, description, path);

        int affectedRow = dao.createFilm(film);
        if (affectedRow == 1) {
            log.info("New film is saved");
            req.setAttribute("info", "New film is saved!");
            dispatcher = req.getRequestDispatcher("html/OK.jsp");
            dispatcher.include(req, resp);
        }
        else {
            log.warn("Error while saving film, maybe it is saved before");
            req.setAttribute("error", "Maybe film was saved before");
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

    private boolean isImage(Part part) {
        String mimeType = getServletContext().getMimeType(part.getSubmittedFileName());
        return mimeType != null && mimeType.startsWith("image/");
    }
}
