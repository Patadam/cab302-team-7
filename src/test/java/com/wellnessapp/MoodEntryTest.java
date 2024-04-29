package com.wellnessapp;

import com.wellnessapp.enums.MoodType;
import com.wellnessapp.model.MoodEntry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class MoodEntryTest {
    private MoodEntry entry;
    private final LocalDateTime time = LocalDateTime.now();
    @BeforeEach
    public void setUp() {
        entry = new MoodEntry(MoodType.HAPPY, time, "example comment");
    }
    @Test
    public void testGetMood() {
        MoodType mood = entry.getMood();
        assertEquals(MoodType.HAPPY, mood);
    }
    @Test
    public void testGetTimestamp() {
        LocalDateTime timestamp = entry.getTimestamp();
        assertEquals(time, timestamp);
    }
    @Test
    public void testGetComment() {
        String comment = entry.getComment();
        assertEquals("example comment", comment);
    }
    @Test
    public void testSetId() {
        int id = 0;
        entry.setId(id);
        assertEquals(0, entry.getId());
    }
    @Test
    public void testSetMood(){
        entry.setMood(MoodType.NEUTRAL);
        assertEquals(MoodType.NEUTRAL, entry.getMood());
    }
    @Test
    public void testSetComment(){
        entry.setComment("new comment");
        assertEquals("new comment", entry.getComment());
    }
    @Test
    public void testSetTimestamp(){
        LocalDateTime time = LocalDateTime.now();
        entry.setTimestamp(time);
        assertEquals(time, entry.getTimestamp());
    }
    @Test
    public void testNewMoodWithOnlyMoodType(){
        MoodEntry entry = new MoodEntry(MoodType.NEUTRAL);
        assertNotNull(entry.getComment());
        assertNotNull(entry.getTimestamp());
    }
}
