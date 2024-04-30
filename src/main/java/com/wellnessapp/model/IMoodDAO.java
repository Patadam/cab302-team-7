package com.wellnessapp.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IMoodDAO {

     void Create(MoodEntry entry) throws SQLException;

     MoodEntry getEntryById(int id);

     void Delete(MoodEntry entry);
     List<MoodEntry> getAllEntries();
}

