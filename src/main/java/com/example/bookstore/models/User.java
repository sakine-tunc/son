package com.example.bookstore.models;

import com.example.bookstore.dao.MySQLDAO;

import java.io.IOException;
import java.sql.Blob;
import java.io.InputStream;
import java.sql.*;

import java.util.ArrayList;

public class User {
    private int id;
    private String username;
    private String password;
    private InputStream image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User login(String username, String password) throws SQLException, ClassNotFoundException {

        Connection connection = MySQLDAO.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT *" +
                " FROM users WHERE username = ? AND password = ?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            User user = new User();

            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));

            return user;
        } else {
            return null;
        }
    }

    public static ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        Connection connection = MySQLDAO.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<User> users = new ArrayList<User>();

        while (resultSet.next()) {
            User user = new User();

            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));

            users.add(user);
        }

        return users;
    }

    public void insert(String username, String password) throws SQLException, ClassNotFoundException {

        Connection connection = MySQLDAO.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users( username, password) " +
                "VALUES (?, ?)");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        preparedStatement.executeUpdate();
        connection.close();
    }

    public static void insert_image(InputStream in) throws SQLException, ClassNotFoundException {

        Connection connection = MySQLDAO.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO images( image) " +
                "VALUES (?)");
        preparedStatement.setBinaryStream(1, in);
        preparedStatement.executeUpdate();
        connection.close();
    }

    public static User getOneById(int id) throws SQLException, ClassNotFoundException {

        User user = new User();

        try {
            Connection connection = MySQLDAO.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();;
        }

        return user;
    }

    public static void delete(int id) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

            connection = MySQLDAO.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?");
        preparedStatement.setInt(1, id);

        preparedStatement.execute();

            connection.close();
    }

    public static void update(int id, String username, String password) throws SQLException, ClassNotFoundException {

        try {
            Connection connection = MySQLDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET username = ?, password = ? WHERE id = ? ");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static User findById(int id) throws SQLException, ClassNotFoundException {
        User user = new User();

        try (Connection connection = MySQLDAO.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public static byte[] getImage(int id) throws SQLException, ClassNotFoundException {
        Connection connection = MySQLDAO.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM images WHERE id = ?");

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
}
