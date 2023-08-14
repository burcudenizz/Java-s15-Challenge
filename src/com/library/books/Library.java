package com.library.books;

import com.library.core.Book;
import com.library.core.Category;
import com.library.core.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Library {
    private List<Book> books;
    Set<Category> categories;
    Map<Book, User> borrowedBooks;

}
