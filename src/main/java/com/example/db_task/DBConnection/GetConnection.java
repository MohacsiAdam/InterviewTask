package com.example.db_task.DBConnection;

import com.example.db_task.CreateEmailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class GetConnection {

    /*Initialize class error logger*/
    Logger logger = LoggerFactory.getLogger(GetConnection.class);


    Connection connection = null;

    public Connection getConnection() {

        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/config/Login.xml"));
            String url = prop.getProperty("URL");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);

        return connection;
    } catch (SQLException throwable) {
            //logger.error("SQL operation error during connection creation, loading stacktrace:"+ throwable.getMessage());
        } catch (InvalidPropertiesFormatException e) {
            //logger.error("Invalid XML format error, loading stacktrace:"+ e.getMessage());
        } catch (FileNotFoundException e) {
            //logger.error("File not found error, loading stacktrace:"+ e.getMessage());
        } catch (IOException e) {
            //logger.error("I/O operation error, loading stacktrace:"+ e.getMessage());
        }
        return null;
    }
}
