package com.example.kinorate.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("MainServlet doGet");
        String requestURI = "/html/main.html";
        if (requestURI.contains("styles.css")) {resp.setContentType("text/css");}

        req.getRequestDispatcher(requestURI).forward(req, resp);
    }
}
