package com.library.service;

import com.library.model.Author;
import com.library.model.Book;
import com.library.model.Category;
import com.library.model.User;

import java.util.List;

public interface LibraryBookService {
    void addBook(Book book);

    Book getBookById(int id);

    List<Book> getBooksByTitle(String title);

    List<Book> getBooksByAuthor(String authorName);

    void updateBook(Book book);

    void deleteBook(int id);

    void deleteBookByTitle(Book book);
    List<Book> getBooksByCategory(Category category);

    List<Book> getBooksByAuthor(Author author);

    void borrowBook(User user, Book book);

    void returnBook(User user, Book book);

    List<Book> getAllBooks();
}
