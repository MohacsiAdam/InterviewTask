package com.example.db_task;

import com.example.db_task.DBConnection.ConnectionProvider;
import com.example.db_task.DBConnection.GetQueryResultsByName;
import com.example.db_task.DBConnection.NameInput;
import com.example.db_task.DBConnection.StatementProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class CreateEmailResponse {

    /*Initialize class error logger*/
    Logger logger = LoggerFactory.getLogger(CreateEmailResponse.class);

    /**
     * The main method responsible for handling the creation of the full letter response
     * and to output the response to console
     */
    public void buildEmailResponse(){

        ConnectionProvider dbConnection = null;
        try {
            dbConnection = ConnectionProvider.getInstance();
        } catch (Throwable throwable) {
            logger.error("Error calling Connection! "+throwable.getMessage());
        }
        Connection connection = Objects.requireNonNull(dbConnection).connection;

        StatementProvider dbStatement = StatementProvider.getInstance(connection);
        Statement statement = dbStatement.statement;

        NameInput nameInput = new NameInput();
        String inputName = nameInput.getNameFromUser();

        GetQueryResultsByName dbResultSet = new GetQueryResultsByName();
        ResultSet resultSet = dbResultSet.getPersonAndCarDataFromQuery(statement, inputName);

        try {
            resultSet.beforeFirst();
            if(!resultSet.next()){
                logger.info("No cars found under this name!");
            }
            else {

                String[] messageParts = getMessageParts(resultSet);

                String messagePersonData = getPersonMessagePart(resultSet, messageParts);
                String messageCarData = getCarMessagePart(resultSet, messageParts);

                String completeMessage = messagePersonData +
                        messageCarData +
                        messageParts[2];
                System.out.println(completeMessage);
            }
        } catch (SQLException throwable) {
          logger.error("SQL database error, loading stacktrace:"+ throwable.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
               } catch (SQLException e) {
                   logger.error("SQL database error during resultSet closing, loading stacktrace:"+ e.getMessage());
                }
            }
            try {
                statement.close();
            } catch (SQLException e) {
               logger.error("SQL database error during statement closing, loading stacktrace:"+ e.getMessage());
            }
            try {
                 connection.close();
             } catch (SQLException e) {
               logger.error("SQL database error during connection closing, loading stacktrace:"+ e.getMessage());
            }
        }
    }

    /**
     *
     * @param resultSet The resultSet obtained with the querry
     * @param messageParts The text of the email separated into 3 parts
     * @return Returns the middle part of the email response with substituted car data for every car under the specified user
     * @throws SQLException --database access errors
     */
    private String getCarMessagePart(ResultSet resultSet, String[] messageParts) throws SQLException {

        StringBuilder sb = new StringBuilder();

        resultSet.first();
        do {
            String message = messageParts[1];

            message = message.replace("<brand>", resultSet.getString("brand"));
            message = message.replace("<type>", resultSet.getString("type"));
            message = message.replace("<plateNumber>", resultSet.getString("plate_number"));
            message = message.replace("<drivenDistance>",Integer.toString(resultSet.getInt("driven_distance")));
            message = message.replace("<calculatedValue>",Integer.toString(resultSet.getInt("calculated_value")));
            message = message.replace("<yearOfManufacture>",Integer.toString(resultSet.getInt("year_of_manufacture")));



            sb.append(message);
        } while (resultSet.next());

        return sb.toString();
    }

    /**
     *
     * @param resultSet The resultSet obtained with the querry
     * @param messageParts The text of the email separated into 3 parts
     * @return A string that will contain the first part of the message filled in with actual Person data
     * @throws SQLException --database access errors
     */
    private String getPersonMessagePart(ResultSet resultSet, String[] messageParts) throws SQLException {

        String message = messageParts[0];
        message = message.replace("<name>", resultSet.getString("name"));
        message = message.replace("<country>", resultSet.getString("country"));
        message = message.replace("<dateOfBirth>", resultSet.getString("data_of_birth"));

        return message;
    }

    /**
     *
     * @param resultSet The resultSet obtained with the querry
     * @return An array of 3 strings containing the following 3 sections of the template email message:
     * 1) Welcoming message and personal information
     * 2) Car information block
     * 3) Letter ending
     */
    private String[] getMessageParts(ResultSet resultSet) {
        String[] messageParts = new String[3];
        String message;
        
        try {
            message = resultSet.getString("text");
            messageParts = message.split("(<carsLoopBegin>|<carsLoopEnd>)");
        } catch (SQLException throwable) {
            logger.error("Error during message part creation:" + throwable.getMessage());
        }

        return messageParts;
    }
}

