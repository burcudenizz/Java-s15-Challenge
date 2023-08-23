package com.library.model;

import com.library.data.LibraryDatabase;
import com.library.service.LibraryBookService;

import java.util.List;

public class BookManager implements LibraryBookService {
    private LibraryDatabase database;

    public BookManager(LibraryDatabase database) {
        this.database = database;
    }

    public void addBook(Book book) {
        database.addBook(book);
    }

    @Override
    public Book getBookById(int id) {
        return database.getBookById(id);
    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        return database.getBooksByTitle(title);
    }

    @Override
    public List<Book> getBooksByAuthor(String authorName) {
        return database.getBooksByAuthor(authorName);
    }

    @Override
    public void updateBook(Book book) {
        database.updateBook(book);
    }

    @Override
    public void deleteBook(int id) {
        database.deleteBook(id);
    }

    @Override
    public List<Book> getBooksByCategory(Category category) {
        return database.getBooksByCategory(category);
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        return database.getBooksByAuthor(author);
    }

    @Override
    public void borrowBook(User user, Book book) {
        if (!book.isBorrowed()) {
            if (user.getBorrowedBooks().size() < 5) {
                book.setBorrowed(true);
                book.setBorrower(user);
                database.borrowBook(user, book);
                double totalPrice = book.getPrice();
                System.out.println("Total price for borrowed book: " + totalPrice);

            } else {
                System.out.println("User has reached the borrowing limit.");
            }
        } else {
            System.out.println("Book is already borrowed by another user.");
        }
    }

    @Override
    public void returnBook(User user, Book book) {
        database.returnBookDatabase(user, book);
    }

    @Override
    public void generateInvoice(User user, Book book) {
//bak???
    }

    @Override
    public List<Book> getAllBooks() {
        return database.getAllBooksDatabase();
    }
}
