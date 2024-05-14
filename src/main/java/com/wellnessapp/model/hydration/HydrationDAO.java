package com.wellnessapp.model.hydration;

import com.wellnessapp.model.DatabaseConnection;

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
                    + "dateTime TIMESTAMP"
                    + ")"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(HydrationEntry entry) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO hydration (dateTime) VALUES (?)");
            statement.setTimestamp(1, Timestamp.valueOf(entry.getDateTime()));
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
            ResultSet r = connection.createStatement().executeQuery("SELECT * FROM hydration ORDER BY dateTime DESC LIMIT 1");
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
            ResultSet r = connection.createStatement().executeQuery("SELECT * FROM hydration ORDER BY dateTime");
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
