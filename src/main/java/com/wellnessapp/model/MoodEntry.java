package com.wellnessapp.model;

import java.time.LocalDateTime;
import com.wellnessapp.enums.MoodType;

public class MoodEntry implements IMoodEntry {
    private int id;
    private LocalDateTime timestamp;
    private MoodType mood;
    public MoodEntry(MoodType mood){
        this.timestamp = LocalDateTime.now();
        this.mood = mood;
    }

//    @Override
//    public LocalDateTime getTimestamp() {
//        return this.timestamp;
//    }
//    public MoodType getMood() {
//        return this.mood;
//    }
//    public int getId(){
//        return this.id;
//    }
}
