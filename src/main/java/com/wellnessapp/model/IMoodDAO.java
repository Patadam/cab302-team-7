package com.wellnessapp.model;

import java.sql.Connection;
import java.util.List;

public interface IMoodDAO {

     void Create(MoodEntry entry);
     void Delete(MoodEntry entry);
     List<MoodEntry> getAll();
}

