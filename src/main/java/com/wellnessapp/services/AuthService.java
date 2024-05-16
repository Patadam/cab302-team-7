package com.wellnessapp.services;

import com.wellnessapp.model.User;

public class AuthService {
    private static AuthService instance;
    private User currentUser;

    private AuthService() {}

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

//    public static User currentUser = null;
//    private AuthService() {}
//    public static User getCurrentUser() {
//        return currentUser;
//    }
//    public void setCurrentUser(User user) {
//        currentUser = user;
//    }

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }
}
