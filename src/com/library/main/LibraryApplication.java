package com.library.main;

import com.library.data.LibraryDatabase;
import com.library.model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApplication {
    static Scanner scanner = new Scanner(System.in);
    static LibraryDatabase database = new LibraryDatabase();
    static UserManager userManager = new UserManager(database);
    static BookManager bookManager = new BookManager(database);

    public static void main(String[] args) {

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
                    processes();

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

    public static void processes() {

        while (true) {

            System.out.println("Please choose an option:");
            System.out.println("1. Add New Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                //the book added to library
                System.out.println("Enter name of the book:");
                String title = scanner.nextLine();

                System.out.println("Enter name of the author:");
                String authorName = scanner.nextLine();

                System.out.println("Enter surname of the author:");
                String authorSurname = scanner.nextLine();

                Author author = new Author(authorName, authorSurname);
                System.out.println("Enter book category: (PHILOSOPHY,NOVEL, STORY, HOBBY, STUDY, EDUCATION, ART, HISTORY, SCIENCEFICTION ");
                Category category = Category.valueOf(scanner.next().toUpperCase());
                Book newBook = new Book(database.generateNewTransactionId(), title, author, category, false);
                bookManager.addBook(newBook);
                System.out.println("Book added successfully!");
                //the book added to library
            }
            if (choice == 2) {
                System.out.println("bye");
            }
        }
    }
}

