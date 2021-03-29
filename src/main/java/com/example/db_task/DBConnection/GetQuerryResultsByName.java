package com.example.db_task.DBConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetQuerryResultsByName {

    ResultSet resultSet = null;

    /*Initialize class error logger*/
    Logger logger = LoggerFactory.getLogger(GetQuerryResultsByName.class);


    public ResultSet getPersonAndCarDataFromQuerry(Statement statement) {

        NameInput nameInput = new NameInput();
        String inputName = nameInput.getNameFromUser();

        String querry = "SELECT " +
                "c.car_id, c.brand, c.type, c.plate_number, c.year_of_manufacture, c.driven_distance, c.calculated_value, p.language_id, p.name, p.country, p.data_of_birth, e.text"+
                " FROM CARS c, Person_data p, Cars_of_people cp, Email_templates e WHERE " +
                "p.name = '" + inputName + "' AND c.car_id=cp.car_id And p.person_id=cp.person_id AND e.language_id=p.language_id AND c.is_sent=0 and c.calculated_value>0";

        try {
            resultSet = statement.executeQuery(querry);
        } catch (SQLException throwable) {
            logger.error("SQL operation error during resultset creation, loading stacktrace:"+ throwable.getMessage());
        }

        return resultSet;
    }
}
