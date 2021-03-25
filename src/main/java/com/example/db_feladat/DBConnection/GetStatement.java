package com.example.db_feladat.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class GetStatement {

    Statement statement = null;

    public Statement getStatement(Connection connection){

        try {
            statement = connection.createStatement();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return statement;
    }
}
