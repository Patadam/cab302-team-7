package com.wellnessapp;

import com.wellnessapp.model.hydration.HydrationEntry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class HydrationEntryTest {
    private HydrationEntry entry;

    @BeforeEach
    public void setUp() {
        entry = new HydrationEntry();
    }

    @Test
    public void testGetIdDoesNotThrowException() {
        assertDoesNotThrow(entry::getId);
    }
    @Test
    public void testSetId() {
        int value = 1234567;
        entry.setId(value);
        assertEquals(value, entry.getId());
    }
    @Test
    public void testGetDateTime() {
        assertEquals(LocalDateTime.class, entry.getDateTime().getClass());
    }
    @Test
    public void testConstructorWithoutDateTime() {
        HydrationEntry entry = new HydrationEntry();
        assertEquals(LocalDateTime.class, entry.getDateTime().getClass());
    }

    @Test
    public void testConstructorWithDateTime() {
        LocalDateTime time = LocalDateTime.now();
        HydrationEntry entry = new HydrationEntry(time);
        assertEquals(time, entry.getDateTime());
    }

}
