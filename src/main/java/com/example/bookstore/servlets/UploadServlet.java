package com.example.bookstore.servlets;

import com.example.bookstore.models.Book;
import com.example.bookstore.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

@WebServlet("/upload")
@MultipartConfig()/*@MultipartConfig bu servlete file upload edileceği bildiriyor tomcate tomcatte bu servlet çağrılırsa
gelen isteği (requesti) inceleyim dosya dosya parçalara bölüyor bizde her dosyayı getPart ile alabiliyoruz*/
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        InputStream in = filePart.getInputStream();// upload dosyasının içini okuyabilmemizi sağlayan objeyi döndürüyor
        System.out.println("Part name is " + filePart.getName());
        try {
            User.insert_image(in);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/upload.jsp");
        requestDispatcher.forward(request, response);

    }
}
