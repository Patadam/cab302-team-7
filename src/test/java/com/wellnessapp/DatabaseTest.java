package com.wellnessapp;

import com.wellnessapp.model.*;

import org.junit.jupiter.api.*;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DatabaseTest {
    @Test
    public void testConnection() {
        Connection conn = DatabaseConnection.getInstance();
        assertEquals(true, conn != null);
    }
}