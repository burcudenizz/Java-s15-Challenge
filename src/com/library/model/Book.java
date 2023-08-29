package com.library.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book {

    private int book_id;
    private String title;
    Author author;
    Category category;
    boolean isBorrowed;
    private double price;
    private User borrower;
    private List<Review> reviews = new ArrayList<>();


    public Book(String title) {
        this.title = title;
        this.reviews = new ArrayList<>();
    }

    public Book(int book_id, String title, Author author, Category category, boolean isBorrowed, double price,User borrower) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isBorrowed = isBorrowed;
        this.price = price;
        this.borrower=borrower;
    }
    public Book(int book_id, String title, Author author, Category category, boolean isBorrowed, double price,User borrower,List<Review> reviews) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isBorrowed = isBorrowed;
        this.price = price;
        this.borrower=borrower;
        this.reviews = new ArrayList<>();
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return book_id == book.book_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", category=" + category +
                ", isBorrowed=" + isBorrowed +
                '}';
    }


}
