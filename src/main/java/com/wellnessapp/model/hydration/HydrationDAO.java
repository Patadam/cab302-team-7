package com.wellnessapp.model.hydration;

import com.wellnessapp.model.DatabaseConnection;
import com.wellnessapp.services.AuthService;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HydrationDAO implements IHydrationDAO {

    public final Connection connection;

    public HydrationDAO() {
        connection = DatabaseConnection.getInstance();
        init();
    }

    public void init() {
        try {
            connection.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS hydration ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "dateTime TIMESTAMP,"
                    + "user_email VARCHAR,"
                    + "FOREIGN KEY (user_email) REFERENCES users(email)"
                    + ")"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(HydrationEntry entry) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO hydration (dateTime, user_email) VALUES (?, ?)");
            statement.setTimestamp(1, Timestamp.valueOf(entry.getDateTime()));
            statement.setString(2, AuthService.getInstance().getCurrentUser().getEmail());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                entry.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void get() {

    }

    @Override
    public void delete() {

    }

    public HydrationEntry getLatestEntry() {
        HydrationEntry entry = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM hydration WHERE user_email = ? ORDER BY dateTime DESC LIMIT 1");
            statement.setString(1, AuthService.getInstance().getCurrentUser().getEmail());
            ResultSet r = statement.executeQuery();
            while (r.next()) {
                int id = r.getInt("id");
                LocalDateTime dateTime = r.getTimestamp("dateTime").toLocalDateTime();
                entry = new HydrationEntry(dateTime);
                entry.setId(id);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return entry;
    }

    @Override
    public List<HydrationEntry> getAll() {
        List<HydrationEntry> entries = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM hydration WHERE user_email = ? ORDER BY dateTime DESC");
            statement.setString(1, AuthService.getInstance().getCurrentUser().getEmail());
            ResultSet r = statement.executeQuery();
            while (r.next()) {
                int id = r.getInt("id");
                LocalDateTime dateTime = r.getTimestamp("dateTime").toLocalDateTime();
                final HydrationEntry entry = new HydrationEntry(dateTime);
                entry.setId(id);
                entries.add(entry);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return entries;
    }


}
