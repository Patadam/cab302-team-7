package com.wellnessapp;

import com.wellnessapp.enums.MoodType;
import com.wellnessapp.model.User;
import com.wellnessapp.model.mood.MoodDAO;
import com.wellnessapp.model.mood.MoodEntry;
import com.wellnessapp.services.AuthService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MoodDAOTest {
    private static Connection con = null;
    private static MoodDAO dao;

   // private static int originalSize;
    @BeforeAll
    public static void setupTestDB() {
        dao = new MoodDAO();
        con = dao.connection;
        AuthService.getInstance().setCurrentUser(new User("DEFAULT_TEST_USER", "DEFAULT_PASSWORD"));
    }

    @BeforeEach
    public void setupTest(){
        try {
            con.setAutoCommit(false);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @AfterEach
    public void clearTest(){
        try {
            con.rollback();
            con.setAutoCommit(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testAddMoodEntryToDatabase() throws SQLException {
        int originalSize = dao.getAllEntries(false).size();
        MoodEntry entry = new MoodEntry(MoodType.NEUTRAL);
        dao.Create(entry);
        assertNotNull(entry);
        assertTrue(entry.getId() > 0);
        assertEquals(originalSize+1, dao.getAllEntries(false).size());
    }

    @Test
    public void testGetMoodEntryFromDatabase() throws SQLException {
        MoodEntry entry = new MoodEntry(MoodType.NEUTRAL);
        dao.Create(entry);

        MoodEntry retrievedEntry = dao.getEntryById(entry.getId());
        assertNotNull(retrievedEntry);
        assertEquals(entry.getId(), retrievedEntry.getId());
        assertEquals(MoodType.NEUTRAL, retrievedEntry.getMood());
        assertEquals("", retrievedEntry.getComment());
        assertNotNull(retrievedEntry.getTimestamp());
    }

    @Test
    public void testGetAllMoodEntriesFromDatabase() throws SQLException {
        int originalSize = dao.getAllEntries(false).size();

        for (int i = 0; i < 6; i++) {
            dao.Create(new MoodEntry(MoodType.NEUTRAL));
        }

        List<MoodEntry> entries = dao.getAllEntries(false);
        assertNotNull(entries);
        assertEquals(originalSize+6, entries.size());

        for (int i = 0; i < 6; i++) {
            MoodEntry entry = entries.get(i);
            assertTrue(entry.getId() > 0);
            assertNotNull(entry.getMood());
            assertNotNull(entry.getComment());
            assertNotNull(entry.getTimestamp());
        }
    }

    @Test
    public void testUpdate() throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime future = now.plusDays(1);
        MoodEntry entry = new MoodEntry(MoodType.NEUTRAL, now, "comment");
        dao.Create(entry);
        assertTrue(entry.getId() > 0);
        entry.setComment("SuperDuperCool");
        entry.setTimestamp(future);
        entry.setMood(MoodType.HAPPY);
        dao.Update(entry);
        MoodEntry retrieved = dao.getEntryById(entry.getId());
        assertEquals("SuperDuperCool", retrieved.getComment());
        assertEquals(future.getDayOfYear(), retrieved.getTimestamp().getDayOfYear());
        assertEquals(MoodType.HAPPY, retrieved.getMood());
    }
}
