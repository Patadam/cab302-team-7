package com.wellnessapp.model.mood;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for Mood Data Access Object (DAO) providing methods to interact with mood entries.
 */
public interface IMoodDAO {
    void Create(MoodEntry entry) throws SQLException;
    void Delete(MoodEntry entry);
    void Update(MoodEntry entry);
    MoodEntry getEntryById(int id);
    List<MoodEntry> getAllEntries(boolean sorted);
}

