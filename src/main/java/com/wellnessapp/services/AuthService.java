package com.wellnessapp.services;

import com.wellnessapp.model.User;

/**
 * Service class responsible for handling user authentication.
 * Implements a singleton pattern to ensure a single instance of AuthService.
 */
public class AuthService {
    private static AuthService instance;
    private User currentUser;

    private AuthService() {}

    /**
     * Sets the current authenticated user.
     * @param user the user to set as the current authenticated user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     * Returns the current authenticated user.
     * @return the current authenticated user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Returns the singleton instance of AuthService.
     * Creates a new instance if one does not already exist.
     * @return the instance of AuthService
     */
    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }
}
