package com.example.db_feladat;

import com.example.db_feladat.DBConnection.GetConnection;
import com.example.db_feladat.DBConnection.GetResultSet;
import com.example.db_feladat.DBConnection.GetStatement;

import java.sql.*;

public class GetData {

    public static void getData(){

        GetConnection dbConnection = new GetConnection();
        Connection connection = dbConnection.getConnection();

        GetStatement dbStatement = new GetStatement();
        Statement statement = dbStatement.getStatement(connection);

        GetResultSet dbResultSet = new GetResultSet();
        ResultSet resultSet = dbResultSet.getResultSet(statement);
        
        try {

            resultSet.beforeFirst();
            if(!resultSet.next()){
                System.out.println("No cars found in database!");
            }
            else {
                StringBuilder sb = new StringBuilder();
                String message = resultSet.getString("text");
                String[] message_parts = message.split("(<carsLoopBegin>|<carsLoopEnd>)");
                String current_part;
                do {
                    /*
                      Beginning part of the message
                      where personal information are shown
                      only needed once
                     */
                    if (resultSet.getRow() == 1) {
                        String name = resultSet.getString("name");
                        Date date_of_birth = resultSet.getDate("data_of_birth");
                        String country = resultSet.getString("country");

                        current_part = message_parts[0];
                        current_part = current_part.replace("<name>", name);
                        current_part = current_part.replace("<country>", country);
                        current_part = current_part.replace("<dateOfBirth>", String.valueOf(date_of_birth));
                        sb.append(current_part);
                    }

                    /*
                      Iterates through the results
                      handling the creation of the middle part of the message
                      for each individual car belonging to the person
                     */
                    current_part = message_parts[1];
                    String brand = resultSet.getString("brand");
                    String type = resultSet.getString("type");
                    String plate = resultSet.getString("plate_number");
                    int year = resultSet.getInt("year_of_manufacture");
                    int value = resultSet.getInt("calculated_value");
                    int distance = resultSet.getInt("driven_distance");

                    current_part = current_part.replace("<brand>", brand);
                    current_part = current_part.replace("<type>", type);
                    current_part = current_part.replace("<plateNumber>", plate);
                    current_part = current_part.replace("<yearOfManufacture>", Integer.toString(year));
                    current_part = current_part.replace("<drivenDistance>", Integer.toString(distance));
                    current_part = current_part.replace("<calculatedValue>", Integer.toString(value));

                    sb.append(current_part);
                    sb.append("\n");
                } while (resultSet.next());

                /*
                  The end of the message requires no substitution so
                  it's appended at the end as is.
                 */
                sb.append(message_parts[2]);
                System.out.println(sb.toString());
            }



        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
               } catch (SQLException e) { /* Ignored */}
            }
            try {
                statement.close();
            } catch (SQLException e) { /* Ignored */}
            try {
                 connection.close();
             } catch (SQLException e) { /* Ignored */}

        }
    }

    public static void main(String[] args) {

        getData();

    }
}

