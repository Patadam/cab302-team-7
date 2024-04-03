package com.example.addressbook;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        connection = DatabaseConnection.getInstance();
    }

    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS users ("
                            + "username VARCHAR NOT NULL, "
                            + "password VARCHAR NOT NULL, "
                            + ")"
            );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    public void insert(User user) {
        // Todo Later: Create a PreparedStatement to run the INSERT query
    }
    public void update(User user) {
        // Todo Later: Create a PreparedStatement to run the UPDATE query
    }
    public void delete(String username) {
        // Todo Later: Create a PreparedStatement to run the DELETE query
    }
    public List<User> getAll() {
        List<User> accounts = new ArrayList<>();
        // Todo Later: Create a Statement to run the SELECT * query
        // and populate the accounts list above
        return accounts;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

}
