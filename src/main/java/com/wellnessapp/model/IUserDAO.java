package com.wellnessapp.model;

import java.util.List;

public interface IUserDAO {
    /**
     * Adds a new user to the database
     * @param user The user to add
     */
    public void insert(User user);

    /**
     * Updates an existing user in the database
     * @param user The user to update
     */
    public void update(User user);

    /**
     * Deletes a user from the database.
     * @param user The user to delete
     */
    public void delete(User user);

    /**
     * Retrieves all users from the database.
     * @return A list of all users in the database
     */
    public List<User> getAll();
}
