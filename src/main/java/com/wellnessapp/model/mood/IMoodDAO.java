package com.wellnessapp.model.mood;

import java.sql.SQLException;
import java.util.List;

public interface IMoodDAO {
     void Create(MoodEntry entry) throws SQLException;
     void Delete(MoodEntry entry);
     MoodEntry getEntryById(int id);
     List<MoodEntry> getAllEntries();
}
