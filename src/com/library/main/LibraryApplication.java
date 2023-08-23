package com.library.main;

import com.library.data.LibraryDatabase;
import com.library.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApplication {
    static Scanner scanner = new Scanner(System.in);
    static LibraryDatabase database = new LibraryDatabase();
    static UserManager userManager = new UserManager(database);
    static BookManager bookManager = new BookManager(database);

    public static void main(String[] args) {

        System.out.println("Welcome to the Library!");
        User loggedInUser = null;
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
                    loggedInUser = database.getUserByEmail(email);
                    processes(loggedInUser);

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

    public static void processes(User loggedInUser) {

        while (true) {

            System.out.println("Please choose an option:");
            System.out.println("1. Add New Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Show All Available Books");
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
                Category category = Category.valueOf(scanner.nextLine().toUpperCase());

                System.out.println("Enter the price of the book:");

                String priceInput = scanner.nextLine().trim();

                if (!priceInput.isEmpty()) {
                    try {
                        double price = Double.parseDouble(priceInput);
                        Book newBook = new Book(database.generateNewTransactionId(), title, author, category, false, price, loggedInUser);
                        bookManager.addBook(newBook);
                        System.out.println("Book added successfully!");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price input. Please enter a valid number.");
                    }
                } else {
                    System.out.println("Invalid price input. Please enter a valid number.");
                }
                //the book added to library
            } else if (choice == 2) {
                //borrow book
                System.out.println("Enter your email:");
                String email = scanner.nextLine();

                System.out.println("Enter book title:");
                String bookTitle = scanner.nextLine();

                User user = database.getUserByEmail(email);
                Book book = bookManager.getBooksByTitle(bookTitle).stream()
                        .filter(b -> !b.isBorrowed())
                        .findFirst()
                        .orElse(null);

                if (user == null || book == null) {
                    System.out.println("User or book not found.");
                } else {
                    bookManager.borrowBook(user, book);
                    System.out.println("Book borrowed successfully!");
                }
                //borrow book
            } else if (choice == 3) {
                //return book
                System.out.println("Enter your email:");
                String email = scanner.nextLine();

                System.out.println("Enter book title:");
                String bookTitle = scanner.nextLine();

                User user = database.getUserByEmail(email);
                Book book = bookManager.getBooksByTitle(bookTitle).stream()
                        .filter(b -> b.isBorrowed() && b.getBorrower().equals(user))
                        .findFirst()
                        .orElse(null);

                if (user == null || book == null) {
                    System.out.println("User or borrowed book not found.");
                } else {
                    bookManager.returnBook(user, book);
                    System.out.println("Book returned successfully!");
                }
            }  else if (choice == 4) {
                // Show All Books
                List<Book> allBooks = bookManager.getAllBooks();

                if (allBooks.isEmpty()) {
                    System.out.println("No books available in the library.");
                } else {
                    System.out.println("All Books:");
                    for (Book book : allBooks) {
                        System.out.println(book);
                    }
                }
            }
            else {
                System.out.println("Invalid choice. Please choose again.");
            }

        }
    }
}

