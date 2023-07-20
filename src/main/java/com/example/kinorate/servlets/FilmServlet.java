package com.example.kinorate.servlets;

import com.example.kinorate.model.Film;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/film")
public class FilmServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the product ID from the request parameter
        int productId = Integer.parseInt(request.getParameter("id"));

        // Retrieve the specific product based on the ID
        Film product = new Film("title", "description", "path");

        if (product != null) {
            // Set the product object as an attribute in the request
            request.setAttribute("product", product);

            // Forward the request to the JSP page for rendering
            request.getRequestDispatcher("/html/film.jsp").forward(request, response);
        } else {
            // Product not found, display an error message
            response.getWriter().println("Product not found.");
        }
    }
}
