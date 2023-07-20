package com.example.kinorate.servlets;

import com.example.kinorate.model.Role;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/mypage")
public class MyPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(req.getRequestURI());
        HttpSession session = req.getSession();


        if (session.getAttribute("role").equals(Role.ADMIN)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("html/adminpage.jsp");
            dispatcher.include(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("html/userpage.jsp");
            dispatcher.include(req, resp);
        }

    }
}
