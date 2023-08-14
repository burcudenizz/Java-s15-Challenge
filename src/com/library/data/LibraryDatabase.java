package com.library.data;

import com.library.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class LibraryDatabase {
    private Map<Integer, Book> books;
    private Map<Integer, User> users;
    private Map<Integer, Transaction> transactions;


    public LibraryDatabase() {
        books = new HashMap<>();
        users = new HashMap<>();
        transactions = new HashMap<>();
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

    public void borrowBook(User user, Book book) { // ?? tekrar bak

        int newTransactionId = generateNewTransactionId();
        Transaction transaction = new Transaction(newTransactionId, user, book, false);
        transactions.put(transaction.getId(), transaction);
        user.getBorrowedBooks().add(book);
        books.put(transaction.getId(), book);

    }

    public void returnBook(User user, Book book) { // ?? tekrar bak
        int newTransactionId = generateNewTransactionId();
        Transaction transaction = transactions.get(book.getBook_id());
        transaction.setReturned(true);
        user.getBorrowedBooks().remove(book);
    }


    @Override
    public String toString() {
        return "LibraryDatabase{" + "books=" + books + ", users=" + users + ", transactions=" + transactions + '}';
    }


}
