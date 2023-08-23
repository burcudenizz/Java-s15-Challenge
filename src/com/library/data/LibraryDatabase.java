package com.library.data;

import com.library.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class LibraryDatabase {

    private AtomicInteger transactionIdCounter = new AtomicInteger(1);

    public int generateNewTransactionId() {
        return transactionIdCounter.getAndIncrement();
    }

    private Map<Integer, Book> books;
    private Map<Integer, User> users;
    private Map<Integer, Transaction> transactions;


    public LibraryDatabase() {
        books = new HashMap<>();
        users = new HashMap<>();
        transactions = new HashMap<>();
    }


    public List<Book> getAllBooksDatabase() {
        return books.values().stream().toList();
    }

    public void addBook(Book book) {
        books.put(book.getBook_id(), book);
    }

    public Book getBookById(int id) {
        return books.get(id);
    }

    public List<Book> getBooksByTitle(String title) {
        return books.values().stream() // tüm kitap nesnelerinin akışını oluşturur.
                .filter(book -> book.getTitle().equals(title)).collect(Collectors.toList()); // filtrelenen kitap nesnelerini bir liste olarak toplar ve döndürür.
    }

    public List<Book> getBooksByAuthor(String authorName) {
        return books.values().stream().filter(book -> book.getAuthor().equals(authorName)).collect(Collectors.toList());
    }

    public void updateBook(Book book) {
        books.put(book.getBook_id(), book);
    }

    public void deleteBook(int id) {
        books.remove(id);
    }

    public List<Book> getBooksByCategory(Category category) {
        return books.values().stream().filter(book -> book.getCategory().equals(category)).collect(Collectors.toList());
    }

    public List<Book> getBooksByAuthor(Author author) {
        return books.values().stream().filter(book -> book.getAuthor().equals(author)).collect(Collectors.toList());
    }

    public void borrowBook(User user, Book book) {

        int newTransactionId = generateNewTransactionId();
        Transaction transaction = new Transaction(newTransactionId, user, book, false);
        transactions.put(transaction.getId(), transaction);
        user.getBorrowedBooks().add(book);
        book.setBorrowed(true);
        //books.remove(book.getBook_id());

    }


    public void returnBookDatabase(User user, Book book) {
        Transaction transaction = transactions.get(book.getBook_id());
        if (transaction != null && !transaction.isReturned()) {
            transaction.setReturned(true);
            book.setBorrowed(false);
            book.setBorrower(null);
            user.getBorrowedBooks().remove(book);
        }
    }

    //USER METHODS

    public void addUser(User user) {
        users.put(user.getUser_id(), user);
    }

    public User getUserById(int id) {
        return users.get(id);
    }

    public User getUserByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public void updateUser(User user) {
        users.put(user.getUser_id(), user);
    }

    public void deleteUser(int id) {
        users.remove(id);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public String toString() {
        return "LibraryDatabase{" + "books=" + books + ", users=" + users + ", transactions=" + transactions + '}';
    }


}
