package com.example.db_feladat.DBConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class GetConnection {

    Connection connection = null;

    public Connection getConnection() {

        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/config/Login.xml"));
            String url = prop.getProperty("URL");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }


        return connection;
    }
}
