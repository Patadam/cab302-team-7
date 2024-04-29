package com.wellnessapp;

import com.wellnessapp.enums.MoodType;
import com.wellnessapp.model.MoodDAO;
import com.wellnessapp.model.MoodEntry;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

        //originalSize = dao.getAllEntries().size();
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
        int originalSize = dao.getAllEntries().size();
        MoodEntry entry = new MoodEntry(MoodType.NEUTRAL);
        dao.Create(entry);
        assertNotNull(entry);
        assertTrue(entry.getId() > 0);
        assertEquals(originalSize+1, dao.getAllEntries().size());
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
        int originalSize = dao.getAllEntries().size();

        for (int i = 0; i < 6; i++) {
            dao.Create(new MoodEntry(MoodType.NEUTRAL));
        }

        List<MoodEntry> entries = dao.getAllEntries();
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
}
