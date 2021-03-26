package com.example.db_task.DBConnection;

import java.util.Scanner;

public class NameInput {

    public String getNameFromUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name:");

        return scanner.nextLine();
    }
}
