package com.example.db_task.DBConnection;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementProvider {

    private static StatementProvider INSTANCE;
    public Statement statement = null;
    Logger logger = LoggerFactory.getLogger(StatementProvider.class);
    
    private StatementProvider(Connection connection){
        try {
            statement = connection.createStatement();


        } catch (SQLException throwable) {
            logger.error("SQL operation error during statement creation, loading stacktrace:" + throwable.getMessage());
        }
    }
    
    public static StatementProvider getInstance(Connection connection){
        if(INSTANCE == null){
            INSTANCE = new StatementProvider(connection);
        }
        return INSTANCE;
    }
}
