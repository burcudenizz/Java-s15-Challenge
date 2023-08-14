package com.library.data;

import com.library.model.Book;
import com.library.model.Transaction;
import com.library.model.User;

import java.util.HashMap;
import java.util.Map;


public class LibraryDatabase {
    private Map<Integer, Book> books;
    private Map<Integer, User> users;
    private Map<Integer, Transaction> transactions;


    public LibraryDatabase() {
        books = new HashMap<>();
        users = new HashMap<>();
        transactions = new HashMap<>();
    }

    /*public void addBook(Book book) {
        books.put(book.getBook_id(), book);
    }

    public Book getBookById(int id) {
        return books.get(id);
    }

    public List<Book> getBooksByTitle(String title) {
        return books.values().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByAuthor(String authorName) {
        return books.values().stream()
                .filter(book -> book.getAuthor().getName().equalsIgnoreCase(authorName))
                .collect(Collectors.toList());
    }

    public void updateBook(Book book) {
        books.put(book.getId(), book);
    }

    public void deleteBook(int id) {
        books.remove(id);
    }

    public List<Book> getBooksByCategory(Category category) {
        return books.values().stream()
                .filter(book -> book.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByAuthor(Author author) {
        return books.values().stream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    public void borrowBook(User user, Book book) {
        Transaction transaction = new Transaction(user, book);
        transactions.put(transaction.getId(), transaction);
        user.getBorrowedBooks().add(book);
        book.setBorrowedBy(user);
    }

    public void returnBook(User user, Book book) {
        Transaction transaction = transactions.get(book.getId());
        transaction.setReturned(true);
        user.getBorrowedBooks().remove(book);
        book.setBorrowedBy(null);
    }

    public Map<Integer, Book> getBooks() {
        return books;
    }

    public void setBooks(Map<Integer, Book> books) {
        this.books = books;
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public void setUsers(Map<Integer, User> users) {
        this.users = users;
    }

    public Map<Integer, Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Map<Integer, Transaction> transactions) {
        this.transactions = transactions;
    }
*/
    @Override
    public String toString() {
        return "LibraryDatabase{" +
                "books=" + books +
                ", users=" + users +
                ", transactions=" + transactions +
                '}';
    }





}
