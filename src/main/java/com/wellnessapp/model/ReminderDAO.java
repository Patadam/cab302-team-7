package com.wellnessapp.model;

import com.wellnessapp.model.notice.NoticeBO;
import com.wellnessapp.model.notice.NoticeManager;

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
                            + "source VARCHAR"
                            + ")"
            );
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void Create(ReminderEntry entry) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO reminders (title, date, time, comments, source) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, entry.getTitle());
            statement.setDate(2, entry.getDate());
            statement.setString(3, entry.getTime());
            statement.setString(4, entry.getComments());
            statement.setString(5, entry.getUrl());
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
            String query = "DELETE FROM reminders WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, entry.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateReminder(ReminderEntry reminder) {
        String query = "UPDATE reminders SET title = ?, date = ?, time = ?, comments = ?, source = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, reminder.getTitle());
            statement.setDate(2, reminder.getDate());
            statement.setString(3, reminder.getTime());
            statement.setString(4, reminder.getComments());
            statement.setString(5, reminder.getUrl());
            statement.setInt(6, reminder.getId());
            statement.executeUpdate();
            LocalDate date = reminder.getDate().toLocalDate();
            LocalTime time = LocalTime.parse(reminder.getTime());
            LocalDateTime dateTime = LocalDateTime.of(date, time);
            System.out.println(dateTime.toString());
            NoticeBO newNotice = NoticeBO.builder().title(reminder.getTitle()).text(reminder.getComments()).build();
            noticeManager.scheduleNotice(dateTime, newNotice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ReminderEntry> getAllEntries() {
        List<ReminderEntry> entries = new ArrayList<>();
        try {
            ResultSet r = connection.createStatement().executeQuery("SELECT * FROM reminders ORDER BY date");
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
