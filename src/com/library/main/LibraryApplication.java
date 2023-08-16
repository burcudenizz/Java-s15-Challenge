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

        System.out.println("Welcome to the Library!");

        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            if (choice == 1) {
                // Register
                System.out.println("Enter your name:");
                String name = scanner.next();

                System.out.println("Enter your surname:");
                String surname = scanner.next();

                System.out.println("Enter your email:");
                String email = scanner.next();

                if (userManager.isEmailRegisteredBefore(email)) {
                    System.out.println("Email is already registered. Please choose another email.");
                } else {
                    System.out.println("Enter your password:");
                    String password = scanner.next();

                    User newUser = new User(database.generateNewTransactionId(), name, surname, email, password, new ArrayList<>());
                    userManager.addUser(newUser);
                    System.out.println("Registration successful!");
                }

            } else if (choice == 2) {
                // Login
                System.out.println("Enter your email:");
                String email = scanner.next();

                System.out.println("Enter your password:");
                String password = scanner.next();

                if (userManager.authenticateUser(email, password)) {
                    System.out.println("Login successful!");
                    break;
                } else {
                    System.out.println("Invalid email or password. Please try again.");
                }
            } else if (choice == 3) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please choose again.");
            }
        }
        scanner.close();
    }









}

