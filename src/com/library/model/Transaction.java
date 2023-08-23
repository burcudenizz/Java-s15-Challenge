package com.library.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private User user;
    private Book book;
    private boolean returned;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private double invoiceAmount;

    public Transaction(int newTransactionId, User user, Book book, boolean returned) {
        this.id = generateNewTransactionId();
        this.user = user;
        this.book = book;
        this.returned = returned;
        this.borrowDate = LocalDateTime.now();
    }

    private int generateNewTransactionId() {
        return 0;
    }

    private double calculateInvoiceAmount() {
        Duration loanDuration = Duration.between(borrowDate, returnDate);
        long days = loanDuration.toDays();
        double bookPrice = book.getPrice();
        double invoiceAmount = days * bookPrice;
        return invoiceAmount;
    }

    public void markReturned() {
        this.returned = true;
        this.returnDate = LocalDateTime.now();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", returned=" + returned +
                '}';
    }
}
