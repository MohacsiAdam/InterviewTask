package com.example.db_task.DBConnection;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class GetStatement {

    /*Initialize class error logger*/
    Logger logger = LoggerFactory.getLogger(GetStatement.class);

    Statement statement = null;

    public Statement getStatement(Connection connection){

        try {
            statement = connection.createStatement();
        } catch (SQLException throwable) {
          logger.error("SQL operation error during statement creation, loading stacktrace:"+ throwable.getMessage());
        }

        return statement;
    }
}
