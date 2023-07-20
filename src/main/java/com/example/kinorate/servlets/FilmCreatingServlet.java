package com.example.kinorate.servlets;

import com.example.kinorate.dao.FilmDao;
import com.example.kinorate.model.Film;
import com.example.kinorate.utills.SavingFiles;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;


@WebServlet("/filmCreating")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 20 * 1024 * 1024)
public class FilmCreatingServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Part filePart = req.getPart("file");
        RequestDispatcher dispatcher;

        if (!isImage(filePart)) {
            dispatcher = req.getRequestDispatcher("html/error.jsp");
            dispatcher.include(req, resp);
            return;
        }

        // Save the file to the server
        String path = SavingFiles.saveImage(filePart, title, "films");
        if (path == null) {
            dispatcher = req.getRequestDispatcher("html/error.jsp");
            dispatcher.include(req, resp);
            return;
        }


        FilmDao dao = new FilmDao();
        Film film = new Film(title, description, path);

        int affectedRow = dao.createFilm(film);
        if (affectedRow == 1) {
            dispatcher = req.getRequestDispatcher("html/OK.jsp");
            dispatcher.include(req, resp);
        }
        else {
            dispatcher = req.getRequestDispatcher("html/error.jsp");
            dispatcher.include(req, resp);
        }

        dispatcher = req.getRequestDispatcher("html/filmcreating.jsp");
        dispatcher.include(req, resp);


    }


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getRequestURI());

        RequestDispatcher dispatcher = req.getRequestDispatcher("html/filmcreating.jsp");
        dispatcher.include(req, resp);
    }

    private boolean isImage(Part part) {
        String mimeType = getServletContext().getMimeType(part.getSubmittedFileName());
        return mimeType != null && mimeType.startsWith("image/");
    }
}
