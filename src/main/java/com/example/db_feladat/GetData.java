package com.example.db_feladat;

import java.sql.*;
import java.util.Scanner;

public class GetData {

    public static void getData(){

        /**
         * Establishing mysql database connection
         */
        String url = "jdbc:mysql://localhost:3306/Cars?useSSL=false";
        String username = "root";
        String password = "Rootpass11";

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        
        try {
            connection = DriverManager.getConnection(url, username, password);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter your name:");
            String inputname = scanner.nextLine();


            String querry = "SELECT " +
                    "c.car_id, c.brand, c.type, c.plate_number, c.year_of_manufacture, c.driven_distance, c.calculated_value, p.language_id, p.name, p.country, p.data_of_birht, e.text"+
                    " FROM CARS c, Person_data p, Cars_of_people cp, Email_templates e WHERE " +
                    "p.name = '" + inputname + "' AND c.car_id=cp.car_id And p.person_id=cp.person_id AND e.language_id=p.language_id AND c.is_sent=0 and c.calculated_value>0";
            statement = connection.createStatement();
            rs = statement.executeQuery(querry);



            rs.beforeFirst();
            if(!rs.next()){
                System.out.println("No cars found in database!");
            }
            else {
                StringBuilder sb = new StringBuilder();
                String message = rs.getString("text");
                String[] message_parts = message.split("(<carsLoopBegin>|<carsLoopEnd>)");
                String current_part;
                do {
                    /**
                     * Beginning part of the message
                     * where personal informations are shown
                     * only needed once
                     */
                    if (rs.getRow() == 1) {
                        String name = rs.getString("name");
                        Date date_of_birth = rs.getDate("data_of_birht");
                        String country = rs.getString("country");

                        current_part = message_parts[0];
                        current_part = current_part.replace("<name>", name);
                        current_part = current_part.replace("<country>", country);
                        current_part = current_part.replace("<dateOfBirth>", String.valueOf(date_of_birth));
                        sb.append(current_part);
                    }

                    /**
                     * Iterates through the results
                     * handling the creation of the middle part of the message
                     * for each individual car belonging to the person
                     */
                    current_part = message_parts[1];
                    String brand = rs.getString("brand");
                    String type = rs.getString("type");
                    String plate = rs.getString("plate_number");
                    int year = rs.getInt("year_of_manufacture");
                    int value = rs.getInt("calculated_value");
                    int distance = rs.getInt("driven_distance");

                    current_part = current_part.replace("<brand>", brand);
                    current_part = current_part.replace("<type>", type);
                    current_part = current_part.replace("<plateNumber>", plate);
                    current_part = current_part.replace("<yearOfManufacture>", Integer.toString(year));
                    current_part = current_part.replace("<drivenDistance>", Integer.toString(distance));
                    current_part = current_part.replace("<calculatedValue>", Integer.toString(value));

                    sb.append(current_part + "\n");
                } while (rs.next());

                /**
                 * The end of the message requires no substitution so
                 * it's appended at the end as is.
                 */
                sb.append(message_parts[2]);
                System.out.println(sb.toString());
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
               } catch (SQLException e) { /* Ignored */}
            }
           if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) { /* Ignored */}
            }
            if (connection != null) {
               try {
                    connection.close();
                } catch (SQLException e) { /* Ignored */}
           }

       }
    }

    public static void main(String[] args) {

        getData();

    }
}

