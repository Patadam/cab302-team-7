package com.wellnessapp;

import com.wellnessapp.model.User;
import com.wellnessapp.model.hydration.HydrationDAO;
import com.wellnessapp.model.hydration.HydrationEntry;
import com.wellnessapp.services.AuthService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

public class HydrationDAOTest {
    private static Connection conn = null;
    private static HydrationDAO dao;

    @BeforeAll
    public static void setupTestDB() {
        dao = new HydrationDAO();
        conn = dao.connection;
        AuthService.getInstance().setCurrentUser(new User("DEFAULT_TEST_USER", "DEFAULT_PASSWORD"));
    }

    @BeforeEach
    public void initTest() {
        try {
            conn.setAutoCommit(false);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void clearTest() {
        try {
            conn.rollback();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddEntryToDatabase() throws SQLException {
        int originalSize = dao.getAll().size();
        HydrationEntry entry = new HydrationEntry();
        dao.insert(entry);
        assertNotNull(entry);
        assertInstanceOf(Integer.class, entry.getId());
        assertEquals(originalSize+1, dao.getAll().size());
    }



}
