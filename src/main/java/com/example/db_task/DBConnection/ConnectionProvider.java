package com.example.db_task.DBConnection;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionProvider {

    private static ConnectionProvider INSTANCE;
    public Connection connection;

    private ConnectionProvider() throws Exception{

        Properties prop = new Properties();

            prop.loadFromXML(new FileInputStream("src/main/resources/config/Login.xml"));
            String url = prop.getProperty("URL");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);

            if(connection==null){
                throw new Exception("Connection returned with NULL value!");
            }
    }

    public static ConnectionProvider getInstance() throws Exception {
        if(INSTANCE == null){
            INSTANCE = new ConnectionProvider();
        }
        return INSTANCE;
    }
}
