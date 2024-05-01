package com.wellnessapp.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.wellnessapp.enums.MoodType;

public class MoodEntry implements IMoodEntry {
    private int id;
    private LocalDateTime timestamp;
    private MoodType value;
    private String comment;

    /**
     * Represents an entry in the mood log with various constructors for different use cases.
     * Each mood entry records the user's mood at a specific time along with optional comments.
     * @param mood The mood recorded in this entry. Should be one of the enum {@link MoodType}.
     * @param timestamp (*) The date and time when the mood changed. This parameter is optional and defaults to the current system time if not specified.
     * @param comment (*) Additional text provided by the user about their mood at the time of the entry. This parameter is optional and defaults to an empty string if not specified.
     */
    public MoodEntry(MoodType mood, LocalDateTime timestamp, String comment) {
        this.timestamp = timestamp;
        this.value = mood;
        this.comment = comment;
    }

    /**
     * Constructs a mood entry with only the mood specified, using the current time as the timestamp and no comment.
     * @param mood The mood recorded in this entry.
     * @see #MoodEntry(MoodType, LocalDateTime, String) MoodEntry(MoodType, LocalDateTime, String) for detailed parameter information.
     */
    public MoodEntry(MoodType mood) {
        this(mood, LocalDateTime.now(), "");
    }

    @Override
    public int getId() {
        return this.id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }
    @Override
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    @Override
    public MoodType getMood(){
        return this.value;
    }
    @Override
    public void setMood(MoodType value) {
        this.value = value;
    }
    @Override
    public String getComment() { return this.comment; }
    @Override
    public void setComment(String comment) { this.comment = comment; }

}
