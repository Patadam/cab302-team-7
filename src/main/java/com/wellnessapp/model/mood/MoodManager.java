package com.wellnessapp.model.mood;

import java.sql.SQLException;
import java.util.List;

/**
 * Manages mood entries, providing methods to add, retrieve, update, and delete mood entries.
 * Allows for future feature improvement and managing of the state and manipulation of data.
 */
public class MoodManager {
    private IMoodDAO moodDAO;

    public MoodManager(IMoodDAO moodDAO) {
        this.moodDAO = moodDAO;
    }

    public void addMoodEntry(MoodEntry entry) throws SQLException { moodDAO.Create(entry); }

    public List<MoodEntry> getAllEntries() {
        return moodDAO.getAllEntries(false);
    }
    public List<MoodEntry> getAllEntries(Boolean sorted) {
        return moodDAO.getAllEntries(sorted);
    }

    public void update(MoodEntry entry) {
        moodDAO.Update(entry);
    }

    public void delete(MoodEntry entry) {
        moodDAO.Delete(entry);
    }
}
