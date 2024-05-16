package com.wellnessapp.model;

import java.sql.SQLException;
import java.util.List;

public interface IReminderDAO {

    void Create(ReminderEntry entry) throws SQLException;

    void CreateDefaultReminders(ReminderEntry entry, String email) throws SQLException;

    void deleteReminder(ReminderEntry entry);

    void updateReminder(ReminderEntry reminder);

    List<ReminderEntry> getAllEntries();
}
