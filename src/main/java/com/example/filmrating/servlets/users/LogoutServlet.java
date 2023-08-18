package com.example.filmrating.servlets.users;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet("/logout")
@Slf4j
public class LogoutServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        log.info("Logout");
        session.setAttribute("isAuthorised", null);
        session.setAttribute("user", null);
        session.setAttribute("role", null);
        RequestDispatcher dispatcher = req.getRequestDispatcher("html/logout.jsp");
        dispatcher.include(req, resp);

    }
}
