package com.wellnessapp.model.mood;

import java.sql.SQLException;
import java.util.List;

public class MoodManager {
    private IMoodDAO moodDAO;

    public MoodManager(IMoodDAO moodDAO) {
        this.moodDAO = moodDAO;
    }

    public void addMoodEntry(MoodEntry entry) throws SQLException { moodDAO.Create(entry); }

    public List<MoodEntry> getAllEntries() {
        return moodDAO.getAllEntries();
    }
}
