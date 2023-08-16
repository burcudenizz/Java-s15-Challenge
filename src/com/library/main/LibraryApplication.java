package com.library.main;

import com.library.data.LibraryDatabase;
import com.library.model.User;
import com.library.model.UserManager;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LibraryDatabase database = new LibraryDatabase();
        UserManager userManager = new UserManager(database);

        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Enter your surname:");
        String surname = scanner.nextLine();
        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        User newUser = new User(database.generateNewTransactionId(), name, surname, email, password, new ArrayList<>());
        userManager.addUser(newUser);
        System.out.println("You are registered to library");




    }
}