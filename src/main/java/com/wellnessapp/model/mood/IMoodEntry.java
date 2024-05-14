package com.wellnessapp.model.mood;


import com.wellnessapp.enums.MoodType;

import java.time.LocalDateTime;

public interface IMoodEntry {
    void setId(int id);
    void setComment(String comment);
    void setTimestamp(LocalDateTime dateTime);
    void setMood(MoodType mood);

    int getId();
    String getComment();
    LocalDateTime getTimestamp();
    MoodType getMood();
}
