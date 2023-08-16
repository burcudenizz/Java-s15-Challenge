package com.library.model;

import com.library.data.LibraryDatabase;
import com.library.service.LibraryUserService;

public class UserManager implements LibraryUserService {

    private LibraryDatabase database;

    public UserManager(LibraryDatabase database) {
        this.database = database;
    }

    @Override
    public void addUser(User user) {
        database.addUser(user);
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        User user = database.getUserByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public void deleteUser(int userId) {
        database.deleteUser(userId);
    }

    public boolean isEmailRegisteredBefore(String email){
        User user= database.getUserByEmail(email);
        return user != null;
    }


}
