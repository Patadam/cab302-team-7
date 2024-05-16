package com.wellnessapp.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;

public class UserDAO implements IUserDAO {
    private Connection connection;
    private ReminderDAO reminderDAO = new ReminderDAO();

    public UserDAO() {
        connection = DatabaseConnection.getInstance();
        createTable();
    }

    private void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS users ("
                            + "email VARCHAR PRIMARY KEY NOT NULL, "
                            + "password VARCHAR NOT NULL "
                            + ")"
            );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    private void insertSampleData() {
        try {
            // Clear before inserting
            Statement clearStatement = connection.createStatement();
            String clearQuery = "DELETE FROM users";
            clearStatement.execute(clearQuery);
            Statement insertStatement = connection.createStatement();
            String insertQuery = "INSERT INTO users (email, password) VALUES "
                    + "('hello@example.com', 'password123'),"
                    + "('janedoe@example.com', 'abc123'),"
                    + "('johndoe@example.com','password')";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void insert(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users " +
                    "(email, password) VALUES (?, ?)");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();

            ReminderEntry screenTime = new ReminderEntry("Screentime Break", Date.valueOf(LocalDate.now()),
                    "14:00", "Remember to take breaks every so often when looking at your screen!",
                    "https://wfmchealth.org/family-health-care/how-to-take-more-screen-breaks-throughout-the-day/");
            ReminderEntry hydrate = new ReminderEntry("Water Break", Date.valueOf(LocalDate.now()),
                    "15:30", "Reminder to drink water often!",
                    "https://www.healthdirect.gov.au/drinking-water-and-your-health");
            ReminderEntry stretchBreak = new ReminderEntry("Stretch Break", Date.valueOf(LocalDate.now()),
                    "11:00", "Reminder to stretch!",
                    "https://newsnetwork.mayoclinic.org/discussion/mayo-clinic-minute-the-importance-of-stretching-throughout-your-workday/");
            reminderDAO.CreateDefaultReminders(screenTime, user.getEmail());
            reminderDAO.CreateDefaultReminders(hydrate, user.getEmail());
            reminderDAO.CreateDefaultReminders(stretchBreak, user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
//    @Override
//    public void update(User user) {
//        try {
//            PreparedStatement statement = connection.prepareStatement("UPDATE users SET email = ?," +
//                    " password = ?");
//            statement.setString(1, user.getEmail());
//            statement.setString(2, user.getPassword());
//            statement.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    public void delete(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE email = ?");
            statement.setString(1, user.getEmail());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                User user = new User(email, password);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public boolean authenticateUser(User user) {
        boolean isAuthenticated = false;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            ResultSet r = statement.executeQuery();
            if (r.next()) {
                isAuthenticated = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return isAuthenticated;
    }

}
