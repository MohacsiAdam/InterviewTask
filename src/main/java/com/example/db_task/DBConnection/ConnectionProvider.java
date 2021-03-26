package com.example.db_task.DBConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class ConnectionProvider {

    private static ConnectionProvider INSTANCE;
    public Connection connection;
    Logger logger = LoggerFactory.getLogger(ConnectionProvider.class);


    /**
     *
     */
    private ConnectionProvider(){

        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/config/Login.xml"));
            String url = prop.getProperty("URL");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);

        }
        catch (SQLException throwable) {
            logger.error("SQL operation error during connection creation, loading stacktrace:"+ throwable.getMessage());
        } catch (FileNotFoundException e) {
            logger.error("File not found error, loading stacktrace:"+ e.getMessage());
        } catch (InvalidPropertiesFormatException e) {
            logger.error("Invalid XML format error, loading stacktrace:"+ e.getMessage());
        }  catch (IOException e) {
            logger.error("I/O operation error, loading stacktrace:"+ e.getMessage());
        }
    }

    public static ConnectionProvider getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ConnectionProvider();
        }
        return INSTANCE;
    }
}
