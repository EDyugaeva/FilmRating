package com.example.kinorate.servlets;

import com.example.kinorate.dao.UserDao;
import com.example.kinorate.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("html/login.jsp");
        dispatcher.include(req, resp);

    }

    //write login to the web-site method
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();

        UserDao dao = new UserDao();
        boolean isValid = dao.validateUser(email, password);

        if (isValid) {
            System.out.println("User exist");
            User user = dao.loginUser(email, password);
            session.setAttribute("isAuthorised", true);
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());

            RequestDispatcher dispatcher = req.getRequestDispatcher("html/login.jsp");
            dispatcher.include(req, resp);

        } else {
            System.out.println("User doesn't exist");

            String html = "<html><h3>Please check your credentials</h3></html>";
            resp.getWriter().write(html+" ");

            RequestDispatcher dispatcher = req.getRequestDispatcher("html/login.jsp");
            dispatcher.include(req, resp);
        }


    }
}
