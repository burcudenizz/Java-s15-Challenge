package com.library.service;

import com.library.model.Book;
import com.library.model.User;

public interface LibraryUserService {

    // Kullanıcıyı kütüphane sistemine kaydetmek için
    void addUser(User user);

    // Kullanıcının giriş yapmasını kontrol etmek için
    boolean authenticateUser(String email, String password);

    // Kullanıcıyı sistemden silmek için
    void deleteUser(int userId);

}
