package com.library.model;

import com.library.data.LibraryDatabase;
import com.library.service.LibraryBookService;

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
        // Check if user can borrow more books
        if (user.getBorrowedBooks().size() < 5) {
            // Check if the book is available
            if (book.getBorrowedBy() == null) {
                database.borrowBook(user, book);
            } else {
                System.out.println("Book is already borrowed by another user.");
            }
        } else {
            System.out.println("User has reached the borrowing limit.");
        }
    }

    @Override
    public void returnBook(User user, Book book) {
        database.returnBook(user, book);
    }

    @Override
    public void generateInvoice(User user, Book book) {
        // Calculate and generate the invoice
    }




}
