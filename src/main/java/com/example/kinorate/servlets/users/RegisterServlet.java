package com.example.kinorate.servlets.users;

import com.example.kinorate.dao.UserDao;
import com.example.kinorate.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "register", value = "/register")
@Slf4j
public class RegisterServlet extends jakarta.servlet.http.HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Register new user");

        String name = req.getParameter("name");
        String lastName = req.getParameter("last-name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String passwordRepeated = req.getParameter("password-repeat");
        LocalDate birthDate = LocalDate.parse(req.getParameter("birth-day"));

        if (!password.equals(passwordRepeated)) {
            System.out.println("Passwords don't match");
            RequestDispatcher dispatcher = req.getRequestDispatcher("html/error.jsp");
            dispatcher.include(req, resp);
        }

        User user = new User(name, lastName, email, password, birthDate);

        UserDao dao = new UserDao();


        int row = dao.registerUser(user);
        if (row == 1) {
            log.info("Register is OK");
            req.setAttribute("info", "You created account, now you can log in");
            req.getRequestDispatcher("html/OK.jsp").include(req, resp);
            req.getRequestDispatcher("html/login.jsp").include(req, resp);

        } else {
            log.warn("Some error occurred during the registration");
            req.setAttribute("error", "Maybe you are registered before");
            req.getRequestDispatcher("html/error.jsp").include(req, resp);
            req.getRequestDispatcher("html/register.jsp").include(req, resp);
        }

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RegisterServlet doGet");
        RequestDispatcher dispatcher = req.getRequestDispatcher("html/register.jsp");
        dispatcher.include(req, resp);

    }
}
