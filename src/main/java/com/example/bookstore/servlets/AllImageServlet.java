package com.example.bookstore.servlets;

import com.example.bookstore.models.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/list-books")
public class AllImageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Book> books = Book.getAll();
            request.setAttribute("books", books);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/books.jsp");
            requestDispatcher.forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException(e);

        }
    }
}