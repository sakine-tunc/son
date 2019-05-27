package com.example.bookstore.models;

import com.example.bookstore.dao.MySQLDAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class Book {
    private int id;
    private String title;
    private InputStream image;

    public static void insert(InputStream in) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public void insert(InputStream image, String title) throws SQLException, ClassNotFoundException {

        Connection connection = MySQLDAO.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book( image, title) " +
                "VALUES (?, ?)");
        preparedStatement.setBinaryStream(1, image);
        preparedStatement.setString(2, title);

        preparedStatement.executeUpdate();
        connection.close();
    }


    public static byte[] getBookById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = MySQLDAO.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE id = ?");

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            Blob image = resultSet.getBlob("image");
            int length = (int)image.length();
            byte[] data = new byte[length];
            InputStream in = image.getBinaryStream();
            try {
                in.read(data);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return data;

        }
        return null;
    }

    public static Book findById(int id) throws SQLException, ClassNotFoundException {
        Book book = new Book();

        try (Connection connection = MySQLDAO.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    public static void delete(int id) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = MySQLDAO.getConnection();
        preparedStatement = connection.prepareStatement("DELETE FROM book WHERE id=?");
        preparedStatement.setInt(1, id);

        preparedStatement.execute();

        connection.close();
    }

    public static ArrayList<Book> getAll() throws SQLException, ClassNotFoundException {
        Connection connection = MySQLDAO.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, title FROM book");
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Book> books = new ArrayList<Book>();

        while (resultSet.next()) {
            Book book = new Book();

            book.setId(resultSet.getInt("id"));
            book.setTitle(resultSet.getString("title"));

            books.add(book);
        }

        return books;
    }
}
