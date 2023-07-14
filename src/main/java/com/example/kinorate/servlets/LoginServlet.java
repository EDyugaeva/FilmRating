package com.example.kinorate.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String html = "<html><h3>Please login</h3></html>";
        resp.getWriter().write(html + " ");

        RequestDispatcher dispatcher = req.getRequestDispatcher("html/login.html");
        dispatcher.include(req, resp);

    }
}
