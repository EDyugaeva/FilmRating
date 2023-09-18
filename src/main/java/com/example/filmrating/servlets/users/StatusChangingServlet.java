package com.example.filmrating.servlets.users;

import com.example.filmrating.model.User;
import com.example.filmrating.services.UserService;
import com.example.filmrating.services.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.NoSuchElementException;

@WebServlet("/status")
@Slf4j
public class StatusChangingServlet extends HttpServlet {
    private static final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long userId = Long.valueOf(req.getParameter("id"));
        log.info("Change status to user with id = {}", userId);

        User user = userService.findById(userId).orElseThrow(()
                -> new NoSuchElementException(String.format("There is no users with that id %d", userId)));

        int changedStatus = Integer.parseInt(req.getParameter("status"));

        int newStatus = user.getStatus() + changedStatus;
        if (newStatus < 0 || newStatus > 100) {
            req.setAttribute("error", "Status should be in range from 0 to 100");
            req.getRequestDispatcher("html/error.jsp").include(req, resp);
            return;
        }
        user.setStatus(newStatus);
        int rowAffected = userService.update(user);
        if (rowAffected == 1) {
            resp.sendRedirect("user?id=" + userId);
        } else {
            log.warn("Error during updating user");
            req.setAttribute("error", "Error while saving data in database");
            req.getRequestDispatcher("html/error.jsp").include(req, resp);
        }
    }
}
