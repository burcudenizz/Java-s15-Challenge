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
        System.out.println("-----l-i-b-r-a-r-y-----");
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
                    System.out.println("-----l-i-b-r-a-r-y-----");
                }

            } else if (choice == 2) {
                // Login
                System.out.println("Enter your email:");
                String email = scanner.next();

                System.out.println("Enter your password:");
                String password = scanner.next();

                if (userManager.authenticateUser(email, password)) {
                    System.out.println("Login successful!");
                    System.out.println("-----l-i-b-r-a-r-y-----");
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
            System.out.println("4. Show All Books with Reviews");
            System.out.println("5. Show Advices According to Category");
            System.out.println("6. Update a Book Info");
            System.out.println("7. Delete a Book");
            System.out.println("8. Show Reviews");
            System.out.println("9. Add Reviews");
            System.out.println("10. Online Chat with Authors");
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
                        System.out.println("-----l-i-b-r-a-r-y-----");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price input. Please enter a valid number.");
                    }
                } else {
                    System.out.println("Invalid price input. Please enter a valid number.");
                }
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
                    System.out.println("-----l-i-b-r-a-r-y-----");
                }
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
                    System.out.println("-----l-i-b-r-a-r-y-----");
                }
            }  else if (choice == 4) {
                // Show All Books
                List<Book> allBooks = bookManager.getAllBooks();

                if (allBooks.isEmpty()) {
                    System.out.println("No books available in the library.");
                } else {
                    System.out.println("All Books with Reviews:");
                    for (Book book : allBooks) {
                        System.out.println("Book Title: " + book.getTitle());
                        System.out.println("Author: " + book.getAuthor().getName() +" "+ book.getAuthor().getSurname());
                        System.out.println("Category: " + book.getCategory());
                        System.out.println("Price: " + book.getPrice());

                        if (book.getReviews() == null) {
                            System.out.println("No reviews available.");
                        } else {
                            System.out.println("Reviews:");
                            for (Review review : book.getReviews()) {
                                System.out.println("Rating: " + review.getRating() + ", Comment: " + review.getComment());
                            }
                        }

                        System.out.println();
                    }
                }
            }
            else if(choice == 5){

                System.out.println("Enter the category:");
                Category category = Category.valueOf(scanner.nextLine());

                List<Book> booksInCategory = bookManager.getBooksByCategory(category);
                if (booksInCategory.isEmpty()) {
                    System.out.println("No books available in this category.");
                } else {
                    System.out.println("Books in " + category + " category:");
                    for (Book book : booksInCategory) {
                        System.out.println(book);
                    }
                }
            }
            else if(choice == 6){

                System.out.println("Enter the title of the book to update:");
                String titleToUpdate = scanner.nextLine();

                List<Book> booksToUpdate = bookManager.getBooksByTitle(titleToUpdate);
                if (booksToUpdate.isEmpty()) {
                    System.out.println("Book not found.");
                } else {
                    Book bookToUpdate = booksToUpdate.get(0); // Choose the first book with the given title
                    System.out.println("Enter new title:");
                    String newTitle = scanner.nextLine();
                    bookToUpdate.setTitle(newTitle);

                    System.out.println("Enter new author name:");
                    String newAuthorName = scanner.nextLine();
                    System.out.println("Enter new author surname:");
                    String newAuthorSurname = scanner.nextLine();
                    bookToUpdate.getAuthor().setName(newAuthorName);
                    bookToUpdate.getAuthor().setSurname(newAuthorSurname);

                    System.out.println("Enter new price:");
                    String newPriceInput = scanner.nextLine().trim();
                    if (!newPriceInput.isEmpty()) {
                        try {
                            double newPrice = Double.parseDouble(newPriceInput);
                            bookToUpdate.setPrice(newPrice);
                            System.out.println("Book information updated successfully!");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid price input. Book information not updated.");
                        }
                    } else {
                        System.out.println("Invalid price input. Book information not updated.");
                    }
                }
            }
            else if (choice == 7) {
                // Delete a Book
                System.out.println("Enter the title of the book to delete:");
                String titleToDelete = scanner.nextLine();

                List<Book> booksToDelete = bookManager.getBooksByTitle(titleToDelete);

                if (!booksToDelete.isEmpty()) {
                    for (Book bookToDelete : booksToDelete) {
                        bookManager.deleteBookByTitle(bookToDelete);
                        System.out.println("Book deleted successfully: " + bookToDelete.getTitle());
                    }
                    System.out.println("-----l-i-b-r-a-r-y-----");
                } else {
                    System.out.println("No books found with the title '" + titleToDelete + "'. Deletion failed.");
                }

            } else if (choice == 8) {
                // Show Reviews
                System.out.println("Enter the title of the book to show reviews:");
                String bookTitle = scanner.nextLine();

                List<Book> booksToShowReviews = bookManager.getBooksByTitle(bookTitle);

                if (!(booksToShowReviews == null)) {
                    for (Book bookToShowReviews : booksToShowReviews) {
                        List<Review> reviews = bookToShowReviews.getReviews();
                        if (reviews.isEmpty()) {
                            System.out.println("No reviews available for the book '" + bookToShowReviews.getTitle() + "'.");
                        } else {
                            System.out.println("Reviews for " + bookToShowReviews.getTitle() + ":");
                            for (Review review : reviews) {
                                System.out.println("Rating: " + review.getRating() + ", Comment: " + review.getComment());
                            }
                        }
                    }
                    System.out.println("-----l-i-b-r-a-r-y-----");
                } else {
                    System.out.println("No books found with the title '" + bookTitle + "'. Review display failed.");
                }
            } else if (choice == 9) {
                // Add Reviews
                System.out.println("Enter the title of the book to add a review:");
                String bookTitle = scanner.nextLine();

                List<Book> booksToAddReview = bookManager.getBooksByTitle(bookTitle);

                if (!booksToAddReview.isEmpty()) {
                    for (Book bookToAddReview : booksToAddReview) {
                        System.out.println("Enter your rating (1-5):");
                        int rating = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Enter your comment:");
                        String comment = scanner.nextLine();

                        Review newReview = new Review(rating, comment);
                        bookToAddReview.addReview(newReview);
                        System.out.println("Review added successfully for the book: " + bookToAddReview.getTitle());
                    }
                    System.out.println("-----l-i-b-r-a-r-y-----");
                } else {
                    System.out.println("No books found with the title '" + bookTitle + "'. Review addition failed.");
                }

            } else if (choice == 10) {
                System.out.println("Select a writer to chat with:");

                List<Author> authors = bookManager.getAllAuthors();
                for (int i = 0; i < authors.size(); i++) {
                    System.out.println((i + 1) + ". " + authors.get(i).getName());
                }

                int authorChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (authorChoice >= 1 && authorChoice <= authors.size()) {
                    Author selectedAuthor = authors.get(authorChoice - 1);

                    System.out.println("Enter your name:");
                    String userName = scanner.nextLine();

                    System.out.println("Enter your email:");
                    String userEmail = scanner.nextLine();

                    System.out.println("Start chatting with " + selectedAuthor.getName() + ":");
                    while (true) {
                        System.out.println("Enter your message (or type 'exit' to end the chat):");
                        String message = scanner.nextLine();

                        if (message.equalsIgnoreCase("exit")) {
                            System.out.println("Chat ended.");
                            break;
                        }

                        System.out.println("Message sent successfully!");
                        System.out.println("-----l-i-b-r-a-r-y-----");
                    }
                } else {
                    System.out.println("Invalid author choice.");
                }
            }

            else {
                System.out.println("Invalid choice. Please choose again.");
            }

        }
    }
}

