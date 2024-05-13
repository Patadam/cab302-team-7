package com.wellnessapp.model;

import java.sql.SQLException;
import java.util.List;

public interface IReminderDAO {

    void Create(ReminderEntry entry) throws SQLException;

    void deleteReminder(ReminderEntry entry);

    List<ReminderEntry> getAllEntries();
}
