package com.example.db_feladat.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class GetResultSet {

    ResultSet resultSet = null;


    public ResultSet getResultSet(Statement statement) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name:");
        String inputName = scanner.nextLine();

        String querry = "SELECT " +
                "c.car_id, c.brand, c.type, c.plate_number, c.year_of_manufacture, c.driven_distance, c.calculated_value, p.language_id, p.name, p.country, p.data_of_birth, e.text"+
                " FROM CARS c, Person_data p, Cars_of_people cp, Email_templates e WHERE " +
                "p.name = '" + inputName + "' AND c.car_id=cp.car_id And p.person_id=cp.person_id AND e.language_id=p.language_id AND c.is_sent=0 and c.calculated_value>0";

        try {
            resultSet = statement.executeQuery(querry);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return resultSet;
    }
}
