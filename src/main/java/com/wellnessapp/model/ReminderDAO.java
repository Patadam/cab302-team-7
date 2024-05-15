package com.wellnessapp.model;

import com.wellnessapp.model.notice.NoticeBO;
import com.wellnessapp.model.notice.NoticeManager;
import com.wellnessapp.services.AuthService;

import java.sql.Connection;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class ReminderDAO implements IReminderDAO{
    public final Connection connection;
    private final NoticeManager noticeManager = new NoticeManager();

    public ReminderDAO() {
        connection = DatabaseConnection.getInstance();
        createTable();
    }
    private void createTable() {
        try {
            connection.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS reminders ("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + "title VARCHAR NOT NULL,"
                            + "date DATE NOT NULL,"
                            + "time VARCHAR NOT NULL,"
                            + "comments VARCHAR,"
                            + "source VARCHAR,"
                            + "user_email VARCHAR,"
                            + "FOREIGN KEY (user_email) REFERENCES users(email)"
                            + ")"
            );
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void Create(ReminderEntry entry) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO reminders (title, date, time, comments, source, user_email) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, entry.getTitle());
            statement.setDate(2, entry.getDate());
            statement.setString(3, entry.getTime());
            statement.setString(4, entry.getComments());
            statement.setString(5, entry.getUrl());
            statement.setString(6, AuthService.getInstance().getCurrentUser().getEmail());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entry.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void CreateDefaultReminders(ReminderEntry entry, String email) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO reminders (title, date, time, comments, source, user_email) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, entry.getTitle());
            statement.setDate(2, entry.getDate());
            statement.setString(3, entry.getTime());
            statement.setString(4, entry.getComments());
            statement.setString(5, entry.getUrl());
            statement.setString(6, email);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entry.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteReminder(ReminderEntry entry) {
        try {
            String query = "DELETE FROM reminders WHERE id = ? AND user_email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, entry.getId());
            statement.setString(2, AuthService.getInstance().getCurrentUser().getEmail());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateReminder(ReminderEntry reminder) {
        String query = "UPDATE reminders SET title = ?, date = ?, time = ?, comments = ?, source = ? WHERE id = ? AND user_email = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, reminder.getTitle());
            statement.setDate(2, reminder.getDate());
            statement.setString(3, reminder.getTime());
            statement.setString(4, reminder.getComments());
            statement.setString(5, reminder.getUrl());
            statement.setInt(6, reminder.getId());
            statement.setString(7, AuthService.getInstance().getCurrentUser().getEmail());
            statement.executeUpdate();
            LocalDate date = reminder.getDate().toLocalDate();
            LocalTime time = LocalTime.parse(reminder.getTime());
            LocalDateTime dateTime = LocalDateTime.of(date, time);
            // System.out.println(dateTime.toString());
            NoticeBO newNotice = NoticeBO.builder().title(reminder.getTitle()).text(reminder.getComments()).build();
            noticeManager.scheduleNotice(dateTime, newNotice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ReminderEntry> getAllEntries() {
        List<ReminderEntry> entries = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reminders WHERE user_email = ? ORDER BY date");
            statement.setString(1, AuthService.getInstance().getCurrentUser().getEmail());
            ResultSet r = statement.executeQuery();
            while (r.next()) {
                int id = r.getInt("id");
                String title = r.getString("title");
                Date date = r.getDate("date");
                String time = r.getString("time");
                String comments = r.getString("comments");
                String url = r.getString("source");

                final ReminderEntry entry = new ReminderEntry(title, date, time, comments, url);
                entry.setId(id);
                entries.add(entry);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return entries;
    }
}
