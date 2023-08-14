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
        return null;
    }

    @Override
    public List<Book> getBooksByAuthor(String authorName) {
        return null;
    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public void deleteBook(int id) {

    }

    @Override
    public List<Book> getBooksByCategory(Category category) {
        return null;
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        return null;
    }

    @Override
    public void borrowBook(User user, Book book) {

    }

    @Override
    public void returnBook(User user, Book book) {

    }

    @Override
    public void generateInvoice(User user, Book book) {

    }


}
