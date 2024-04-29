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
     * Constructs a mood entry with a specified timestamp but without any comment.
     * @param mood The mood recorded in this entry.
     * @param timestamp The date and time when the mood was recorded.
     * @see #MoodEntry(MoodType, LocalDateTime, String) MoodEntry(MoodType, LocalDateTime, String) for detailed parameter information.
     */
    public MoodEntry(MoodType mood, LocalDateTime timestamp) {
        this(mood, timestamp, "");
    }

    /**
     * Constructs a mood entry with a comment but uses the current time as the timestamp.
     * @param mood The mood recorded in this entry.
     * @param comment Additional text about the mood.
     * @see #MoodEntry(MoodType, LocalDateTime, String) MoodEntry(MoodType, LocalDateTime, String) for detailed parameter information.
     */
    public MoodEntry(MoodType mood, String comment) {
        this(mood, LocalDateTime.now(), comment);
    }

    /**
     * Constructs a mood entry with only the mood specified, using the current time as the timestamp and no comment.
     * @param mood The mood recorded in this entry.
     * @see #MoodEntry(MoodType, LocalDateTime, String) MoodEntry(MoodType, LocalDateTime, String) for detailed parameter information.
     */
    public MoodEntry(MoodType mood) {
        this(mood, LocalDateTime.now(), "");
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public MoodType getMood(){
        return this.value;
    }
    public void setMood(MoodType value) {
        this.value = value;
    }

    public String getComment() { return this.comment; }
    public void setComment(String comment) { this.comment = comment; }

}
